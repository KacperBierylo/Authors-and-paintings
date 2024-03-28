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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/paintings")
public class PaintingController {

    private PaintingService paintingService;
    private AuthorService authorService;

    @Autowired
    public PaintingController(PaintingService paintingService, AuthorService authorService){
        this.paintingService = paintingService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetPaintingsResponse> getPaintings() {
        List<Painting> all = paintingService.findAll();
        Function<Collection<Painting>, GetPaintingsResponse> mapper = GetPaintingsResponse.entityToDtoMapper();
        GetPaintingsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPaintingResponse> getPainting(@PathVariable("id") long id) {

        return  paintingService.find(id)
                .map(value -> ResponseEntity.ok(GetPaintingResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPainting(@RequestBody CreatePaintingRequest request, UriComponentsBuilder builder) {
        Optional<Author> author = authorService.find(request.getAuthor());
        if(author.isEmpty()) {
            System.out.println("nie znalazl") ;return ResponseEntity.notFound().build();}
        System.out.println(author.get().getId());
        Painting painting = CreatePaintingRequest
                .dtoToEntityMapper(id -> authorService.find(id).orElseThrow())
                .apply(request);
        painting = paintingService.create(painting);
        return ResponseEntity.created(builder.pathSegment("api", "paintings", "{id}")
                .buildAndExpand(painting.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable("id") long id) {
        Optional<Painting> painting = paintingService.find(id);
        if (painting.isPresent()) {
            paintingService.delete(painting.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePainting(@RequestBody UpdatePaintingRequest request, @PathVariable("id") long id) {
        Optional<Painting> painting = paintingService.find(id);
        if (painting.isPresent()) {

            UpdatePaintingRequest.dtoToEntityUpdater(authorService).apply(painting.get(), request);
            paintingService.update(painting.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

