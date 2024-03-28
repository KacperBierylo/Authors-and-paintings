package lab.painting.controller;

import lab.painting.dto.*;
import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lab.painting.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(PaintingService paintingService, AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        Author author = CreateAuthorRequest
                .dtoToEntityMapper(id -> authorService.find(id).orElseThrow())
                .apply(request);
        author = authorService.create(author);
        return ResponseEntity.created(builder.pathSegment("api", "authors", "{id}")
                .buildAndExpand(author.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") long id) {
        Optional<Author> author = authorService.find(id);
        if (author.isPresent()) {
            authorService.delete(author.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
