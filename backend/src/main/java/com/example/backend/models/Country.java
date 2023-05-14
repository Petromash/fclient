package com.example.backend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.*;

@Entity
@Table(name = "countries")
@Access(AccessType.FIELD)
public class Country {
    public Country() { }
    public Country(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    public List<Artist> artists = new ArrayList<Artist>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
