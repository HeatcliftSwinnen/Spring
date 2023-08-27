package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Participation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmallParticipationDTO {

    private Long competitionId;      // Identifiant de la compétition associée à la participation
    private String competitionName;  // Nom de la compétition associée à la participation
    private Integer position;        // Position dans la compétition

    // Méthode de conversion de l'entité Participation en SmallParticipationDTO
    public static SmallParticipationDTO toDTO(Participation entity){
        if( entity == null )
            return null;

        return SmallParticipationDTO.builder()
                .competitionId( entity.getCompetition().getId() )
                .competitionName( entity.getCompetition().getName() )
                .position( entity.getPosition() )
                .build();
    }
}
