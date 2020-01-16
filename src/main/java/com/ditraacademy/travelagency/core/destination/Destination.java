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

    @JsonIgnore //elle s'applique sur la liste de voyage car elle vient avant, elle enléve cet attribut de JSON, on la utilisé dans notre cas pour enlever la boucle infinie
    @OneToMany(mappedBy = "destination") // facultatif si on l'applique on parle d'une relation bidirectionnel, destination a accés au voyage,mapped by on lajout pour définir l'attribut dans voyage je vais mettre le num1 sur l'attribut
    private List<Voyage> voyages;
}
