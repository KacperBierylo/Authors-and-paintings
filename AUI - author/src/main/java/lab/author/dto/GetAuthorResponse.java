package lab.author.dto;

import lab.author.entity.Author;
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
public class GetAuthorResponse {
    private Long id;
    private String nameAndSurname;
    private int yearOfBirth;


    public static Function<Author, GetAuthorResponse> entityToDtoMapper() {
        return author -> GetAuthorResponse.builder()
                .id(author.getId())
                .nameAndSurname(author.getNameAndSurname())
                .yearOfBirth(author.getYearOfBirth())
                .build();
    }
}
