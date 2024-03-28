package lab.painting.controller;

import lab.painting.dto.CreatePaintingRequest;
import lab.painting.dto.GetPaintingResponse;
import lab.painting.dto.GetPaintingsResponse;
import lab.painting.dto.UpdatePaintingRequest;
import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lab.painting.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/authors/{authorId}/paintings")
public class AuthorPaintingController {

    private PaintingService paintingService;

    private AuthorService authorService;


    @Autowired
    public AuthorPaintingController(PaintingService paintingService, AuthorService authorService) {
        this.paintingService = paintingService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetPaintingsResponse> getPaintings(@PathVariable("authorId") Long authorId) {
        Optional<Author> author = authorService.find(authorId);
        return author.map(value -> ResponseEntity.ok(GetPaintingsResponse.entityToDtoMapper().apply(paintingService.findByAuthor(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("{id}")
    public ResponseEntity<GetPaintingResponse> getPainting(@PathVariable("authorId") Long authorId,
                                                             @PathVariable("id") long id) {
        return paintingService.findByAuthorAndId(authorId, id)
                .map(value -> ResponseEntity.ok(GetPaintingResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    @PostMapping
    public ResponseEntity<Void> createPainting(@PathVariable("authorId") Long authorId,
                                                @RequestBody CreatePaintingRequest request,
                                                UriComponentsBuilder builder) {
        request.setAuthor(authorId);
        Optional<Author> author = authorService.find(authorId);
        if (author.isPresent()) {
            Painting painting = CreatePaintingRequest
                    .dtoToEntityMapper(id -> authorService.find(id).orElseThrow())
                    .apply(request);
            painting = paintingService.create(painting);
            return ResponseEntity.created(builder.pathSegment("api", "authors", "{authorId}", "paintings", "{id}")
                    .buildAndExpand(author.get().getId(), painting.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable("authorId") Long authorId,
                                                @PathVariable("id") long id) {
        Optional<Painting> painting = paintingService.findByAuthorAndId(authorId, id);
        if (painting.isPresent()) {
            paintingService.delete(painting.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePainting(@PathVariable("authorId") Long authorId,
                                                @RequestBody UpdatePaintingRequest request,
                                                @PathVariable("id") long id) {
        request.setAuthor(authorId);
        Optional<Painting> painting = paintingService.findByAuthorAndId(authorId, id);
        if (painting.isPresent()) {
            UpdatePaintingRequest.dtoToEntityUpdater(authorService).apply(painting.get(), request);
            paintingService.update(painting.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
