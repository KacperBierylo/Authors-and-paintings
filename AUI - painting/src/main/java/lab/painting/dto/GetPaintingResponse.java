package lab.painting.dto;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetPaintingResponse {
   // private Long id;
    private String title;
    private Long authorId;
    private int year;

    public static Function<Painting, GetPaintingResponse> entityToDtoMapper() {
        return painting -> GetPaintingResponse.builder()
            //    .id(painting.getId())
                .title(painting.getTitle())
                .authorId(painting.getAuthor().getId())
                //.author(painting.getAuthor().getNameAndSurname())
                .year(painting.getYear())
                .build();
    }
}
