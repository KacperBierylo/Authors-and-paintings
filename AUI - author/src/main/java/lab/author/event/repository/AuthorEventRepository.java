package lab.author.event.repository;

import lab.author.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import lab.author.event.dto.CreateAuthorRequest;
@Repository
public class AuthorEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public AuthorEventRepository(@Value("${app.paintings.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Author author) {
        restTemplate.delete("/authors/{authorId}", author.getId());
    }

    public void create(Author author) {
        restTemplate.postForLocation("/authors", CreateAuthorRequest.entityToDtoMapper().apply(author));
    }
}
