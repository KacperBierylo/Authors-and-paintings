package lab.author.controller;

import lab.author.dto.*;
import lab.author.entity.Author;
import lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthors() {
        List<Author> all = authorService.findAll();
        Function<Collection<Author>, GetAuthorsResponse> mapper = GetAuthorsResponse.entityToDtoMapper();
        GetAuthorsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("id") long id) {

        return authorService.find(id)
                .map(value -> ResponseEntity.ok(GetAuthorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        Author author = CreateAuthorRequest.dtoToEntityMapper().apply(request);
        authorService.create(author, false);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "authors", "{authorId}")
                        .buildAndExpand(author.getId()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") long id) {
        Optional<Author> author = authorService.find(id);
        if (author.isPresent()) {
            authorService.delete(author.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAuthor(@RequestBody UpdateAuthorRequest request, @PathVariable("id") long id) {
        Optional<Author> author = authorService.find(id);
        if (author.isPresent()) {
            UpdateAuthorRequest.dtoToEntityUpdater().apply(author.get(), request);
            authorService.update(author.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
