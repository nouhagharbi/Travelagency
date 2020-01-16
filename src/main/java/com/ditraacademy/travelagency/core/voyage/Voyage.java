package com.ditraacademy.travelagency.core.voyage;


import com.ditraacademy.travelagency.core.destination.Destination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id qui s'incrémente auto
    private int id;
    private String titre;
    private String description;
    private double nbPlaces;
    private Double prix;
    private Date date;

    @ManyToOne
    private Destination destination;

}
