package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Game;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmallGameDTO {
    private Long id;      // Identifiant du jeu
    private String name;  // Nom du jeu

    // Méthode de conversion de l'entité Game en SmallGameDTO
    public static SmallGameDTO toDTO(Game entity){
        if( entity == null )
            return null;

        return SmallGameDTO.builder()
                .id( entity.getId() )
                .name( entity.getName() )
                .build();
    }
}