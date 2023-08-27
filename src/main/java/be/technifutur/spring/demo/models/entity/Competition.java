package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compet_id", nullable = false)
    private Long id;

    @Column(name = "compet_name", nullable = false)
    private String name;

    @Column(name = "compet_inscription_start", nullable = false)
    private LocalDate inscriptionStart;

    @Column(name = "compet_inscription_end", nullable = false)
    private LocalDate inscriptionEnd;

    @Column(name = "compet_start", nullable = false)
    private LocalDate competitionStart;

    @Column(name = "compet_end", nullable = false)
    private LocalDate competitionEnd;

    @Column(name = "compet_entry", nullable = false)
    private double entry;

    @Column(name = "compet_cash_prize", nullable = false)
    private double cashPrice;

    @Column(name = "compet_distribution_mode", nullable = false)
    private DistributionMode distributionMode;

    @ManyToOne
    @JoinColumn(name = "compet_address", nullable = false)
    private Address address; // Relation many-to-one avec l'entité Address

    @ManyToOne
    @JoinColumn(name = "compet_game_id", nullable = false)
    private Game gamePlayed; // Relation many-to-one avec l'entité Game

    @OneToMany(mappedBy = "competition")
    private Set<Participation> participations; // Relation one-to-many avec l'entité Participation

}
