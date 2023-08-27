package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id", nullable = false)
    private Long id;

    @Column(name = "studio_name", nullable = false)
    private String name; // Nom du studio de jeux

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "studio_address_id", nullable = false)
    private Address address; // Adresse du studio, relation many-to-one avec l'entité Address

    @OneToMany(mappedBy = "studio")
    private List<Game> games; // Liste des jeux développés par ce studio, relation one-to-many avec l'entité Game

}
