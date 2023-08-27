package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.models.dto.GameDTO;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.models.form.GameForm;
import be.technifutur.spring.demo.models.form.GamePlatformsForm;
import be.technifutur.spring.demo.models.form.GamePriceForm;
import be.technifutur.spring.demo.service.GameService;
import be.technifutur.spring.demo.service.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Ce contrôleur gère les opérations liées aux jeux
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final StudioService studioService;

    // Constructeur
    public GameController(GameService gameService, StudioService studioService) {
        this.gameService = gameService;
        this.studioService = studioService;
    }

    // Récupère un jeu par son identifiant
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<GameDTO> getOne(@PathVariable long id){
        Game game = gameService.getGame(id);
        GameDTO body = GameDTO.toDTO(game);
        return ResponseEntity.ok(body);
    }

    // Récupère tous les jeux, éventuellement filtrés par prix
    @GetMapping
    public ResponseEntity<List<GameDTO>> getAll(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ){
        List<Game> games = gameService.getAllGames(minPrice, maxPrice);
        List<GameDTO> body = games.stream()
                .map(GameDTO::toDTO)
                .toList();
        return ResponseEntity.ok( body );
    }

    // Supprime un jeu par son identifiant
    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(@PathVariable long id){
        gameService.removeGame(id);
        return ResponseEntity.ok("deleted");
    }

    // Crée un nouveau jeu
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody GameForm form){
        Game entity = form.toEntity();
        Studio studio = studioService.getOne( form.getStudioId() );
        entity.setStudio( studio );

        long body = gameService.addGame( entity );
        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( body );
    }

    // Met à jour un jeu existant
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<GameDTO> update(@PathVariable long id, @RequestBody GameForm form){
        Game entity = form.toEntity();
        Studio studio = studioService.getOne( form.getStudioId() );
        entity.setStudio( studio );

        Game game = gameService.updateGame( id, entity );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }

    // Met à jour le prix d'un jeu
    @PatchMapping("/{id:[0-9]+}/price")
    public ResponseEntity<GameDTO> updatePrice(@PathVariable long id, @RequestBody GamePriceForm form){
        Game game = gameService.updatePrice( id, form.getPrice() );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }

    // Ajoute des plateformes à un jeu
    @PatchMapping("/{id:[0-9]+}/platforms")
    public ResponseEntity<GameDTO> addPlatform(@PathVariable long id, @RequestBody GamePlatformsForm form){
        Game game = gameService.addPlatform( id, form.getPlatforms() );
        GameDTO body = GameDTO.toDTO( game );
        return ResponseEntity.ok( body );
    }
}
