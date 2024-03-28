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
public class CreateAuthorRequest {
    private String nameAndSurname;
    private int yearOfBirth;

    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper() {
        return request -> Author.builder()
                .nameAndSurname(request.getNameAndSurname())
                .yearOfBirth(request.getYearOfBirth())
                .build();
    }
}
