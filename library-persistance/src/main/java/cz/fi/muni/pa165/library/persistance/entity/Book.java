package cz.fi.muni.pa165.library.persistance.entity;

import cz.fi.muni.pa165.library.persistance.entity.base.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *Entity representing book stored in library
 * 
 * @author Martin Palenik
 */

@Entity
public class Book implements EntityBase {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String author;

    @NotNull
    @Column(nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", orphanRemoval = true)
    private Set<LoanItem> loanItems = new HashSet<>();
    
    public Book() {
    }

    public Book(Long id) {
        this.id = id;
    }
    
    public Book(String author, String title){
        this.author = author;
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.author);
        hash = 67 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
