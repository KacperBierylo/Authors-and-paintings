package lab.author.event.dto;

import lab.author.entity.Author;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateAuthorRequest {
   // private Long id;
    private String nameAndSurname;
    private int year;
    public static Function<Author, CreateAuthorRequest> entityToDtoMapper() {
        return entity -> CreateAuthorRequest.builder()
                //.id(entity.getId())
                .nameAndSurname(entity.getNameAndSurname())
                .year(entity.getYearOfBirth())
                .build();
    }
}
