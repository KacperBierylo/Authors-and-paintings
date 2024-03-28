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
//@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateAuthorRequest {
    //private String nameAndSurname;
    //private int yearOfBirth;
    //private Long id;
    public static Function<CreateAuthorRequest, Author> dtoToEntityMapper(
            Function<Long, Author> authorFunction) {
        return request -> Author.builder()
                //.nameAndSurname(request.getNameAndSurname())
                //.yearOfBirth(request.getYearOfBirth())
                .build();
    }
}
