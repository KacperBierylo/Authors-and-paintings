package lab.configuration;


import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lab.painting.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final AuthorService authorService;
    private final PaintingService paintingService;

    @Autowired
    public InitializedData(AuthorService authorService, PaintingService paintingService) {
        this.authorService = authorService;
        this.paintingService = paintingService;
    }

    @PostConstruct
    private synchronized void init() {

        Author janMatejko = Author.builder()
                .build();
        Author jacekMalczewski = Author.builder()
                .build();

        authorService.create(janMatejko);
        authorService.create(jacekMalczewski);

        Painting bitwaPodGrunwaldem = Painting.builder()
                .title("Bitwa pod Grunwaldem")
                .author(janMatejko)
                .year(1878)
                .build();

        Painting anielePojdeZaToba = Painting.builder()
                .title("Aniele pójdę za Tobą")
                .author(jacekMalczewski)
                .year(1901)
                .build();
        paintingService.create(bitwaPodGrunwaldem);
        paintingService.create(anielePojdeZaToba);
        //authorService.delete(3L);
        //authorService.delete(4L);
    }


}
