package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jt on 12/23/19.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Author john = new Author("john", "tom");
        Book ddd = new Book("Domain Driven Design", "123123");
        Book springSecurity = new Book("Spring Security", "456456");
        eric.getBooks().add(ddd);

        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);

        publisher.getBooks().add(ddd);


        authorRepository.save(eric);

        bookRepository.save(ddd);
        bookRepository.save(springSecurity);
        publisherRepository.save(publisher);


        eric.getBooks().add(springSecurity);
        springSecurity.getAuthors().add(eric);
        springSecurity.setPublisher(publisher);
        publisher.getBooks().add(springSecurity);
        bookRepository.save(springSecurity);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
         publisher.getBooks().stream().forEach(book -> {System.out.println(book.getTitle());});
        List<Author> list = (List<Author>) authorRepository.findAll();
        System.out.println("Erick books: " +  list.stream().findFirst().get().getBooks());
    }
}
