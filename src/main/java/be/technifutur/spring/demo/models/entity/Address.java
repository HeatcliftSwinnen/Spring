package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.*;

// Cette classe représente une entité "Address" stockée en base de données
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    // Identifiant généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    // Rue de l'adresse
    @Column(name = "address_street", nullable = false)
    private String street;

    // Numéro de l'adresse
    @Column(name = "address_number", nullable = false)
    private String number;

    // Ville de l'adresse
    @Column(name = "address_city", nullable = false)
    private String city;

    // Code postal de l'adresse
    @Column(name = "address_zipcode", nullable = false)
    private String zipcode;

    // Pays de l'adresse
    @Column(name = "address_country", nullable = false)
    private String country;
}
