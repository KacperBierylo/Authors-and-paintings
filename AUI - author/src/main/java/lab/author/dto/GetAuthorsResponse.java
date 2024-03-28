package lab.author.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetAuthorsResponse {

    @Singular
    private List<Author> authors;


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Author{
        private Long id;
        private String nameAndSurname;
        //private int yearOfBirth;
    }

    public static Function<Collection<lab.author.entity.Author>, GetAuthorsResponse> entityToDtoMapper() {
        return authors -> {
            GetAuthorsResponse.GetAuthorsResponseBuilder response = GetAuthorsResponse.builder();
            authors.stream()
                    .map(author -> Author.builder()
                            .id(author.getId())
                            .nameAndSurname(author.getNameAndSurname())
                            //.yearOfBirth(author.getYearOfBirth())
                            .build())
                    .forEach(response::author);
            return response.build();
        };
    }

}
