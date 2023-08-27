package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class GameDTO {

    private Long id;                 // Identifiant du jeu
    private String name;             // Nom du jeu
    private Set<Genre> genres;       // Genres du jeu
    private LocalDate releaseDate;   // Date de sortie du jeu
    private String studioName;       // Nom du studio associé au jeu
    private double price;            // Prix du jeu
    private Set<Platform> platforms; // Plateformes sur lesquelles le jeu est disponible
    private Set<SmallCompetitionDTO> competitions; // Compétitions associées au jeu

    // Méthode de conversion de l'entité Game en DTO
    public static GameDTO toDTO(Game entity){
        if( entity == null )
            return null;

        return GameDTO.builder()
                .id( entity.getId() )
                .name( entity.getName() )
                .genres( entity.getGenres() )
                .releaseDate( entity.getReleaseDate() )
                .platforms( entity.getPlatforms() )
                .price( entity.getPrice() )
                .studioName( entity.getStudio().getName() )
                .competitions(
                        entity.getCompetitions().stream()
                                .map(SmallCompetitionDTO::toDTO)
                                .collect(Collectors.toSet())
                )
                .build();
    }

}
