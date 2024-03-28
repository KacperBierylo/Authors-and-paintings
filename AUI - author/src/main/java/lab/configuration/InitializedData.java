package lab.configuration;


import lab.author.entity.Author;
import lab.author.event.repository.AuthorEventRepository;
import lab.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final AuthorService authorService;
    private final AuthorEventRepository eventRepository;
    @Autowired
    public InitializedData(AuthorService authorService, AuthorEventRepository eventRepository) {
        this.authorService = authorService;
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    private synchronized void init() {

        Author janMatejko = Author.builder()
                .nameAndSurname("Jan Matejko")
                .yearOfBirth(1838)
                .build();
        Author jacekMalczewski = Author.builder()
                .nameAndSurname("Jacek Malczewski")
                .yearOfBirth(1854)
                .build();

        authorService.create(janMatejko, true);
        authorService.create(jacekMalczewski, true);
        //eventRepository.delete(janMatejko2);
        //eventRepository.delete(jacekMalczewski2);
    }


}
