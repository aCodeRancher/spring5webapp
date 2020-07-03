package guru.springframework.spring5webapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;

    private String state;

    private String zip;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    public Publisher(String name, String address, String state, String zip){
        this.name = name;
        this.address = address;
        this.state = state;
        this.zip = zip;
    }

    public Publisher(){

    }


    public String toString(){
        return "Publisher{" +
                "id=" + id +
                ", name="+ name +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", books=" + books.stream().map(book-> book.getTitle()).collect(Collectors.joining(","))+
                "}";

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return id != null ? id.equals(publisher.id) : publisher.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
