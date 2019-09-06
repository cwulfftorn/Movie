package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movies"),
@NamedQuery(name = "Movie.getAll", query = "SELECT m FROM Movies m"),
@NamedQuery(name = "Movie.getByName", query = "SELECT m FROM Movies m WHERE m.name LIKE :name")})
public class Movies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String name;
    private String mainactors;
    
    public Movies(int year, String name, String mainactors) {
        this.year = year;
        this.name = name;
        this.mainactors = mainactors;
    }
    
    public Movies() {
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainactors() {
        return mainactors;
    }

    public void setMainactors(String mainactors) {
        this.mainactors = mainactors;
    }
}
