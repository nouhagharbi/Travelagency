package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {

    @Autowired
    VoyageRepository voyageRepository ;

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> addVoyage(Voyage voyage) {

        Optional<Destination> destinationOptional= destinationRepository.findById(voyage.getDestination().getId());

        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel(" Invalid voyage"), HttpStatus.BAD_REQUEST);

        voyage.setDestination(destinationOptional.get());

        voyage  = voyageRepository.save(voyage);
        return  new ResponseEntity<>(voyage,HttpStatus.OK);
    }

    public ResponseEntity<?> updateVoyage(int id , Voyage voyage) {
        Optional<Voyage> optionalVoyage =  voyageRepository.findById(id);

        if (!optionalVoyage.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel(" Invalid voyage"), HttpStatus.BAD_REQUEST);

        Voyage voyageLegacy = optionalVoyage.get();

        if ( voyage.getDate()!=null ||   voyageLegacy.getDate() != voyage.getDate())
            voyageLegacy.setDate(voyage.getDate());

        if (   voyage.getDescription()!=null || !(voyageLegacy.getDescription().equals(voyage.getDescription())))
            voyageLegacy.setDescription(voyage.getDescription());

        if (  voyage.getNbPlaces()!=voyageLegacy.getNbPlaces())
            voyageLegacy.setNbPlaces(voyage.getNbPlaces());

        if ( voyage.getPrix()!=voyageLegacy.getPrix())
            voyageLegacy.setPrix(voyage.getPrix());

        if ( voyage.getTitre()!= null || !(voyage.getTitre().equals(voyageLegacy.getTitre())))
            voyageLegacy.setTitre(voyage.getTitre());

        voyageRepository.save(voyageLegacy);

        return  new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getVoyage(int id) {
        Optional<Voyage> optionalVoyage  =  voyageRepository.findById(id);

        if(!(optionalVoyage.isPresent()))
            return  new ResponseEntity<>(new ErrorResponseModel("ID not found "), HttpStatus.BAD_REQUEST);

        return  new ResponseEntity<>(optionalVoyage.get(),HttpStatus.OK) ;
    }

    public ResponseEntity<?> getAllVoyages() {
        List<Voyage> voyages = voyageRepository.findAll();
        return  new  ResponseEntity<>(voyages ,HttpStatus.OK) ;
    }

    public ResponseEntity<?> deleteVoyage(int id) {
        Optional<Voyage> optionalVoyage =  voyageRepository.findById(id);

        if (!optionalVoyage.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("id not found"), HttpStatus.BAD_REQUEST);

        voyageRepository.delete(optionalVoyage.get());
        return   new ResponseEntity<>(HttpStatus.OK)  ;
    }



    public ResponseEntity<?> getAllVoyagePrixMax(double prix){
        List<Voyage> voyageprix =voyageRepository.findAllByPrixLessThanEqualAndNbPlacesIsNot(prix,0);
        return new ResponseEntity<>(voyageprix,HttpStatus.OK);
    }
}
