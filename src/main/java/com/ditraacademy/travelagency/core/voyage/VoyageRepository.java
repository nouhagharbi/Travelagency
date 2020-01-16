package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.core.chambre.chambre.ChambreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

    List<Voyage> findAllByPrixLessThanEqualAndNbPlacesIsNot(double maxPrice, double nbzeroplace);

}
