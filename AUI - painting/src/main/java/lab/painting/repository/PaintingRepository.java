package lab.painting.repository;

import lab.datastore.DataStore;
import lab.painting.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import lab.painting.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

    Optional<Painting> findById(Long id);

    List<Painting> findAllByAuthor(Author author);
    List<Painting> findAll();
    Optional <Painting> findByAuthorAndId(Author author, Long id);
}
