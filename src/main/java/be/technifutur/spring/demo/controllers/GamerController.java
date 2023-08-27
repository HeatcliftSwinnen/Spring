package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.models.dto.GamerDTO;
import be.technifutur.spring.demo.models.form.GamerForm;
import be.technifutur.spring.demo.service.GamerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamer")
public class GamerController {

    private final GamerService gamerService;

    // Injection du service GamerService via le constructeur
    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    // Crée un nouveau gamer
    @PostMapping
    public ResponseEntity<Long> addGamer(@RequestBody @Valid GamerForm form){
        return ResponseEntity.status( HttpStatus.CREATED )
                .body( gamerService.add(form.toEntity()) );
    }

    // Récupère la liste de tous les gamers
    @GetMapping
    public ResponseEntity<List<GamerDTO>> getAll(){
        return ResponseEntity.ok(
                gamerService.getAll().stream()
                        .map(GamerDTO::toDTO)
                        .toList()
        );
    }

    // Récupère un gamer par son ID
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<GamerDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok( GamerDTO.toDTO(gamerService.getOne(id)) );
    }

    // Met à jour les informations d'un gamer par son ID
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid GamerForm form){
        gamerService.update( id, form.toEntity() );
        return ResponseEntity.noContent()
                .build();
    }

    // Supprime un gamer par son ID
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        gamerService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    // Ajoute un jeu à la liste de jeux joués par un gamer spécifique
    @PatchMapping("/{gamerId:[0-9]+}/add_game")
    public ResponseEntity<?> addGame(@PathVariable Long gamerId, @RequestParam Long gameId ){
        gamerService.addGame(gamerId, gameId);
        return ResponseEntity.noContent()
                .build();
    }
}
