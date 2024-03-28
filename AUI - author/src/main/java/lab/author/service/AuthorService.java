package lab.author.service;

import lab.author.entity.Author;
import lab.author.event.repository.AuthorEventRepository;
import lab.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;
    private AuthorEventRepository eventRepository;

    @Autowired
    public AuthorService(AuthorRepository repository, AuthorEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Author> find(Long id) {
        return repository.findById(id);
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Author author, boolean init) {
        repository.save(author);
        if(!init) eventRepository.create(author);
    }

    @Transactional
    public void update(Author author) {
        repository.save(author);
    }

    @Transactional
    public void delete(Author author) {
        repository.delete(author);
        eventRepository.delete(author);
    }
}
