package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Cette classe représente une entité "Game" stockée en base de données
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    // Identifiant généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id", nullable = false)
    private Long id;

    // Nom du jeu
    @Column(name="game_name", nullable = false)
    private String name;

    // Genres du jeu (relation "Many-to-Many" avec Enum "Genre")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Genre> genres = new HashSet<>();

    // Date de sortie du jeu
    @Column(name = "game_release")
    private LocalDate releaseDate;

    // Prix du jeu
    @Column(name = "game_price", nullable = false)
    private double price;

    // Plateformes du jeu (relation "Many-to-Many" avec Enum "Platform")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Platform> platforms = new HashSet<>();

    // Studio qui a développé le jeu (relation "Many-to-One" avec entité "Studio")
    @ManyToOne
    @JoinColumn(name = "game_studio_id", nullable = false)
    private Studio studio;

    // Compétitions associées au jeu (relation "One-to-Many" avec entité "Competition")
    @OneToMany(mappedBy = "gamePlayed")
    private Set<Competition> competitions = new HashSet<>();
}
