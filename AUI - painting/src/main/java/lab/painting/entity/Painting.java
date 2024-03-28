package lab.painting.entity;


import lombok.*;

import java.io.Serializable;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "paintings")
public class Painting implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int year;
    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

}
