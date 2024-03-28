package lab.painting.repository;

import lab.painting.entity.Author;
import lab.datastore.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(Long id);


    List<Author> findAll();
}
