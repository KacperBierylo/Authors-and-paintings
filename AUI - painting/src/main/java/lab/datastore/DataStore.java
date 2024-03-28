package lab.datastore;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.serialization.CloningUtility;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataStore {

//    private Set<Painting> paintings = new HashSet<>();
//    private Set<Author> authors = new HashSet<>();
//
//    public synchronized List<Painting> findAllPaintings() {
//        return new ArrayList<>(paintings);
//        //return paintings.stream().map(CloningUtility::clone).collect(Collectors.toList());
//    }
//
//    public synchronized List<Author> findAllAuthors() {
//        return new ArrayList<>(authors);
//    }
//
//    public Optional<Painting> findPainting(Long id) {
//        return paintings.stream()
//                .filter(painting -> painting.getId().equals(id))
//                .findFirst();
//                //.map(CloningUtility::clone);
//    }
//
//    public Optional<Author> findAuthor(Long id) {
//        return authors.stream()
//                .filter(author -> author.getId().equals(id))
//                .findFirst();
//                //.map(CloningUtility::clone);
//    }
//
//    public synchronized void createAuthor(Author author) throws IllegalArgumentException {
//        author.setId(findAllAuthors().stream().mapToLong(Author::getId).max().orElse(0) + 1);
//        authors.add(author);
//    }
//
//    public synchronized void createPainting(Painting painting) throws IllegalArgumentException {
//        painting.setId(findAllPaintings().stream().mapToLong(Painting::getId).max().orElse(0) + 1);
//        paintings.add(painting);
//    }
//
//    public synchronized void deleteAuthor(Long id) throws IllegalArgumentException {
//        findAuthor(id).ifPresentOrElse(
//                original -> authors.remove(original),
//                () -> {
//                    throw new IllegalArgumentException(
//                            String.format("The author with ID \"%d\" does not exist", id));
//                });
//    }
//
//    public synchronized void deletePainting(Long id) throws IllegalArgumentException {
//        findPainting(id).ifPresentOrElse(
//                original -> paintings.remove(original),
//                () -> {
//                    throw new IllegalArgumentException(
//                            String.format("The painting with ID \"%d\" does not exist", id));
//                });
//    }
}
