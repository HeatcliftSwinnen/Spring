package be.technifutur.spring.demo.models.dto;

import be.technifutur.spring.demo.models.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private Long id;         // Identifiant de l'adresse
    private String street;   // Rue
    private String number;   // Numéro de rue
    private String city;     // Ville
    private String zipcode;  // Code postal
    private String country;  // Pays

    // Méthode de conversion de l'entité Address en DTO
    public static AddressDTO toDTO(Address entity){
        if( entity == null )
            return null;

        return AddressDTO.builder()
                .id( entity.getId() )
                .street(entity.getStreet())
                .number(entity.getNumber())
                .city(entity.getCity())
                .zipcode(entity.getZipcode())
                .country(entity.getCountry())
                .build();
    }
}
