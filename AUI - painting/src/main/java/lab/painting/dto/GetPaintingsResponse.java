package lab.painting.dto;

import lab.painting.entity.Painting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetPaintingsResponse {

    @Singular
    private List<Painting> paintings;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Painting{
        private Long id;
        private String title;
        private Long authorId;
        //private int year;
    }

    public static Function<Collection<lab.painting.entity.Painting>, GetPaintingsResponse> entityToDtoMapper() {
        return paintings -> {
            GetPaintingsResponseBuilder response = GetPaintingsResponse.builder();
            paintings.stream()
                    .map(painting -> Painting.builder()
                            .id(painting.getId())
                            .title(painting.getTitle())
                            //.year(painting.getYear())
                            .authorId(painting.getAuthor().getId())
                            .build())
                    .forEach(response::painting);
            return response.build();
        };
    }
}
