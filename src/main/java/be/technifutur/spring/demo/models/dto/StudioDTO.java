package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Studio;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class StudioDTO {

    private Long id;               // Identifiant du studio
    private String name;           // Nom du studio
    private AddressDTO address;    // Adresse du studio
    private List<SmallGameDTO> games; // Liste des jeux du studio

    // Méthode de conversion de l'entité Studio en StudioDTO
    public static StudioDTO toDTO(Studio entity){
        if (entity == null)
            return null;

        return StudioDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address( AddressDTO.toDTO(entity.getAddress()) ) // Conversion de l'adresse en AddressDTO
                .games(
                        entity.getGames().stream()
                                .map(SmallGameDTO::toDTO)
                                .toList()
                )
                .build();
    }

}
