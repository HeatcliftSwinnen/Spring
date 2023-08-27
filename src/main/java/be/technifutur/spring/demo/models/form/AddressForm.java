package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressForm {

    @NotBlank
    private String street; // Rue de l'adresse, doit être non vide

    @NotBlank
    private String number; // Numéro de l'adresse, doit être non vide

    @NotBlank
    private String city; // Ville de l'adresse, doit être non vide

    @NotBlank
    private String zipcode; // Code postal de l'adresse, doit être non vide

    @NotBlank
    private String country; // Pays de l'adresse, doit être non vide

    public Address toEntity(){
        Address address = new Address();
        address.setStreet( this.street );
        address.setNumber( this.number );
        address.setCity( this.city );
        address.setZipcode( this.zipcode );
        address.setCountry( this.country );
        return address; // Convertit l'AddressForm en une entité Address
    }
}
