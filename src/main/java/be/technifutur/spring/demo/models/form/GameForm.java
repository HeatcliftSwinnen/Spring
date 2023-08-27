package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import be.technifutur.spring.demo.validation.contraints.Exclude;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class GameForm {

    @NotBlank
    @Size(min = 5)
    private String name; // Nom du jeu, ne doit pas être vide et doit avoir une longueur minimale de 5 caractères

    @Exclude(enumClazz = Genre.class, values = {"FPS", "RTS"})
    private Set<Genre> genres; // Ensemble de genres du jeu (FPS, TPS, RPG, etc.) avec exclusion de certains genres

    @NotNull
    @Past
    private LocalDate releaseDate; // Date de sortie du jeu, ne doit pas être nulle et doit être dans le passé

    @NotNull
    private Long studioId; // ID du studio associé au jeu, ne doit pas être nul

    @NotNull
    @PositiveOrZero
    private Double price; // Prix du jeu, ne doit pas être nul et doit être positif ou nul

    @Size(min = 1)
    @Exclude(enumClazz = Platform.class, values = {"PC", "SWITCH"})
    private Set<Platform> platforms; // Plateformes sur lesquelles le jeu est disponible, avec exclusion de certaines plateformes

    public Game toEntity(){
        Game game = new Game();
        game.setName( this.name );
        game.setGenres( this.genres );
        game.setReleaseDate( this.releaseDate );
        game.setPrice( this.price );
        game.setPlatforms( this.platforms );
        return game; // Convertit le GameForm en une entité Game
    }
}
