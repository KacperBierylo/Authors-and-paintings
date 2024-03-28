package lab.painting.dto;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreatePaintingRequest {

    private String title;
    private Long author;
    private int year;


    public static Function<CreatePaintingRequest, Painting> dtoToEntityMapper(

            Function<Long, Author> authorFunction) {
        return request -> Painting.builder()
                .title(request.getTitle())
                .year(request.getYear())
                .author(authorFunction.apply(request.getAuthor()))
                .build();
    }
}
