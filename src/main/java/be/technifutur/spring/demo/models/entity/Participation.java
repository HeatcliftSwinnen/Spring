package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participation {

    // Clé composite
    @EmbeddedId
    private ParticipationId id;

    // Joueur participant (relation "Many-to-One" avec entité "Gamer")
    @ManyToOne
    @MapsId("gamerId")
    @JoinColumn(name = "part_gamer_id")
    private Gamer gamer;

    // Compétition à laquelle le joueur participe (relation "Many-to-One" avec entité "Competition")
    @ManyToOne
    @MapsId("competId")
    @JoinColumn(name = "part_compet_id")
    private Competition competition;

    // Position du joueur dans la compétition (peut être null)
    @Column(name = "part_position", nullable = true)
    private Integer position;

    // Classe imbriquée définissant la clé composite
    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParticipationId implements Serializable {

        // Identifiant du joueur
        private Long gamerId;

        // Identifiant de la compétition
        private Long competId;

    }
}
