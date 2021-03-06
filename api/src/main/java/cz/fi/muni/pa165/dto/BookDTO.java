package cz.fi.muni.pa165.dto;

import java.util.Objects;

/**
 * Data Transfer object for Book entity
 * 
 * @author Martin Palenik
 */
public class BookDTO {
    
    private Long id;
    private String author;
    private String title;
    
    public BookDTO() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public int hashCode() {
        int hash = 8;
        hash = 52 * hash + Objects.hashCode(this.title);
        hash = 52 * hash + Objects.hashCode(this.author);
        hash = 52 * hash + Objects.hashCode(this.id);
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
        
        final BookDTO other = (BookDTO) obj;
        
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return true;
    }
}