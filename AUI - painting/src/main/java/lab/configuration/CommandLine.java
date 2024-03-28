package lab.configuration;

import lab.painting.entity.Author;
import lab.painting.entity.Painting;
import lab.painting.service.AuthorService;
import lab.painting.service.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private Scanner scanner = new Scanner(System.in);
    private PaintingService paintingService;
    private AuthorService authorService;

    @Autowired
    public void CommandLine(PaintingService paintingService, AuthorService authorService) {
        this.paintingService = paintingService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("all")) {
                paintingService.findAll().forEach(System.out::println);
                authorService.findAll().forEach(System.out::println);
            } else if (command.equals("allAuthors")) {
                authorService.findAll().forEach(System.out::println);
            } else if (command.equals("allPaintings")) {
                paintingService.findAll().forEach(System.out::println);
            } else if (command.equals("addAuthor")) {
                System.out.println("write name and surname: ");
                String nameAndSurname = scanner.nextLine();
                System.out.println("write year of birth: ");
                int yearOfBirth = Integer.parseInt(scanner.nextLine());
                authorService.create(Author.builder()
                        //.nameAndSurname(nameAndSurname)
                        //.yearOfBirth(yearOfBirth)
                        .build());
            } else if (command.equals("addPainting")) {
                Author author;
                System.out.println("write author's ID");
                Long authorID = Long.valueOf(scanner.nextLine());
                Optional<Author> optionalAuthor = authorService.find(authorID);
                if (optionalAuthor.isPresent()) {
                    author = (Author) optionalAuthor.get();
                    System.out.println("write title: ");
                    String title = scanner.nextLine();
                    System.out.println("write year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    paintingService.create(Painting.builder()
                            .author(author)
                            .title(title)
                            .year(year)
                            .build());
                }


            } else if (command.equals("deleteAuthor")) {
                System.out.println("write author's ID");
                Long id = Long.valueOf(scanner.nextLine());
                authorService.delete(id);
            } else if (command.equals("deletePainting")) {
                System.out.println("write painting ID");
                Long id = Long.valueOf(scanner.nextLine());
                paintingService.delete(id);
            } else if (command.equals("quit")) {
                System.exit(0);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

}
