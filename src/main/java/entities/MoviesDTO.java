/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author christianwulff
 */
public class MoviesDTO {
    
    private Long id;
    private int year;
    private String name;
    private String mainactors;
    
    public MoviesDTO() {
    }

    public MoviesDTO(Long id, int year, String name, String mainactors) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.mainactors = mainactors;
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
