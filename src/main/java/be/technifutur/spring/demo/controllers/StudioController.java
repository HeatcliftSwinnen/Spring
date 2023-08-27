package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.models.dto.StudioDTO;
import be.technifutur.spring.demo.models.form.StudioForm;
import be.technifutur.spring.demo.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {

    private final StudioService studioService;

    // Injection du service StudioService via le constructeur
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    // Ajoute un nouveau studio
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid StudioForm form){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( studioService.add( form.toEntity() ) );
    }

    // Récupère la liste de tous les studios
    @GetMapping
    public ResponseEntity<List<StudioDTO>> findAll(){
        return ResponseEntity.ok(
                studioService.getAll().stream()
                        .map( StudioDTO::toDTO )
                        .toList()
        );
    }

    // Récupère un studio par son ID
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<StudioDTO> findOne(@PathVariable Long id){
        return ResponseEntity.ok( StudioDTO.toDTO(studioService.getOne(id)) );
    }

    // Met à jour les informations d'un studio par son ID
    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid StudioForm form){
        studioService.update(id, form.toEntity());
        return ResponseEntity.noContent()
                .build();
    }

    // Supprime un studio par son ID
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studioService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
