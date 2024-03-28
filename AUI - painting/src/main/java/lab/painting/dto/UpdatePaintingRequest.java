package lab.painting.dto;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.function.BiFunction;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdatePaintingRequest {
    private String title;
    private Long author;
    private int year;


    public static BiFunction<Painting, UpdatePaintingRequest, Painting> dtoToEntityUpdater(AuthorService authorService) {
        return (painting, request) -> {
            painting.setTitle(request.getTitle());
            painting.setYear(request.getYear());
            painting.setAuthor(authorService.find(request.getAuthor()).get());
            return painting;
        };
    }
}
