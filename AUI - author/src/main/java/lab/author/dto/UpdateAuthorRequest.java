package lab.author.dto;

import lab.author.entity.Author;
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
public class UpdateAuthorRequest {
    private String nameAndSurname;
    private int yearOfBirth;


    public static BiFunction<Author, UpdateAuthorRequest, Author> dtoToEntityUpdater() {
        return (painting, request) -> {
            painting.setNameAndSurname(request.getNameAndSurname());
            painting.setYearOfBirth(request.getYearOfBirth());
            return painting;
        };
    }
}
