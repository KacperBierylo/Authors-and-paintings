package lab.painting.service;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.repository.AuthorRepository;
import lab.painting.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaintingService {

    private PaintingRepository repository;
    private AuthorRepository authorRepository;
    @Autowired
    public PaintingService(PaintingRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public Optional<Painting> find(Long id) {
        return repository.findById(id);
    }

    public Optional <Painting> findByAuthorAndId(Long authorId, Long id){
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return repository.findByAuthorAndId(author.get(), id);
        } else {
            return Optional.empty();
        }
    }

    public List<Painting> findAll() {
        return repository.findAll();
    }

    public List<Painting> findByAuthor(Author author){return repository.findAllByAuthor(author);}

    @Transactional
    public Painting create(Painting painting) {
        return repository.save(painting);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);

    }
    @Transactional
    public void update(Painting painting) {
        repository.save(painting);
    }
}
