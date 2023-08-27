package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Gamer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gamer_id", nullable = false)
    private Long id;

    @Column(name = "gamer_pseudo", nullable = false, unique = true)
    private String pseudo; // Pseudo unique pour chaque joueur

    @Column(name = "gamer_email", nullable = false, unique = true)
    private String email; // Adresse e-mail unique pour chaque joueur

    @Column(name = "gamer_password", nullable = false)
    private String password;

    @Column(name = "gamer_birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gamer_active", nullable = false)
    private boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "games_played",
            joinColumns = @JoinColumn(name = "gamer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name= "game_id", nullable = false)
    )
    private Set<Game> gamesPlayed = new HashSet<>(); // Relation many-to-many avec l'entité Game

    @OneToMany(mappedBy = "gamer")
    private Set<Participation> participations = new HashSet<>(); // Relation one-to-many avec l'entité Participation

}
