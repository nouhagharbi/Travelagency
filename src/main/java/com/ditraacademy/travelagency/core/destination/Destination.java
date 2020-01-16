package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id qui s'incrémente auto
    private int id;
    private String nom;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "destination")
    private List<Voyage> voyages;
}
