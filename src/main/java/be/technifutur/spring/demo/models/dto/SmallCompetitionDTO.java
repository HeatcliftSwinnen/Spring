package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Competition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmallCompetitionDTO {

    private Long id;
    private String name;
    private CompetitionStatus status;

    public static SmallCompetitionDTO toDTO(Competition entity){
        if( entity == null )
            return null;

        return SmallCompetitionDTO.builder()
                .id( entity.getId() )
                .name( entity.getName() )
                .status( CompetitionStatus.getStatus(entity) )
                .build();
    }

}