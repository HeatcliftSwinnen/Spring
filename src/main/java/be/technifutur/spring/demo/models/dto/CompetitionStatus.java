package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Competition;
import java.time.LocalDate;

public enum CompetitionStatus {
    INSCRIPTION_NOT_OPEN,
    INSCRIPTION_OPEN,
    INSCRIPTION_CLOSED,
    STARTED,
    FINISHED;

    public static CompetitionStatus getStatus(Competition competition){

        LocalDate currentDate = LocalDate.now();

        if( currentDate.isBefore(competition.getInscriptionStart()) )
            return INSCRIPTION_NOT_OPEN;

        if( currentDate.isBefore(competition.getInscriptionEnd()) )
            return INSCRIPTION_OPEN;

        if( currentDate.isBefore(competition.getCompetitionStart()) )
            return INSCRIPTION_CLOSED;

        if( currentDate.isBefore(competition.getCompetitionEnd()) )
            return STARTED;

        return FINISHED;
    }
}
