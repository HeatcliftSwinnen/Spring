package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.exceptions.DuplicateUniqueIdException;
import be.technifutur.spring.demo.models.JeuxVideo;
import be.technifutur.spring.demo.services.JeuxVideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class JeuxVideoController {
    private final JeuxVideoService jeuxVideoService;

    //Constructor
    public JeuxVideoController(JeuxVideoService jeuxVideoService) {
        this.jeuxVideoService = jeuxVideoService;
    }

    //Gestion des exceptions dans le controller
    @ExceptionHandler(DuplicateUniqueIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //Error 409  , il y a un conflict avec les données déja présente
    public ResponseEntity<String> handleDuplicateUniqueIdException(DuplicateUniqueIdException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //Error 404 , pas trouvé
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Add
    @PostMapping({"/jeuxVideo","/jeuxVideo/add"})
    public void addJeuxVideo(@RequestBody JeuxVideo jeuxVideoToAdd){
        jeuxVideoService.addJeuxVideo(jeuxVideoToAdd);
    }

    //Delete
    @DeleteMapping("/jeuxVideo/{id:[0-9]+}")
    public void deleteJeuxVideo(@PathVariable("id")int id){
       jeuxVideoService.deleteJeuxVideo(id);
    }

    //Get
    @GetMapping("/jeuxVideo/{id:[0-9]+}")
    public JeuxVideo getJeuxVideoById(@PathVariable("id")int id){
        return jeuxVideoService.getJeuVideo(id);
    }

    // GetAll
    @GetMapping("/jeuxVideo/all")
    public List<JeuxVideo> getAllJeuxVideo() {
        List<JeuxVideo> jeuxVideoList = jeuxVideoService.getAll();
        return List.copyOf(jeuxVideoList);
    }

    //ModifyPrice
    @PatchMapping("/jeuxVideo/{id:[0-9]+}/price")
    public void modifyPrice(@PathVariable("id")int id ,@RequestBody double newPrice){
        jeuxVideoService.modifyPrice(id ,newPrice);
    }

    //ModifyAll
    @PutMapping("/jeuxVideo/{id:[0-9]+}")
    public void modifyJeuxVideo(@PathVariable("id")int id,@RequestBody JeuxVideo jeuxVideoUpdated){
        jeuxVideoService.modifyAll(id,jeuxVideoUpdated);
    }

    //AddPlatform
    @PatchMapping("/jeuxVideo/{id:[0-9]+}/platform")
    public void addPlatform(@PathVariable("id")int id,@RequestBody String newPlatform){
        jeuxVideoService.addPlatform(id,newPlatform);
    }
}
