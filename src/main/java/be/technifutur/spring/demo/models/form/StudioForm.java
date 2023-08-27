package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Studio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudioForm {

    @NotBlank
    private String name; // Nom du studio, ne doit pas être vide

    @Valid
    private AddressForm address; // Adresse du studio, valide grâce à l'annotation @Valid

    public Studio toEntity(){
        Studio studio = new Studio();
        studio.setName( this.name ); // Affecte le nom du studio

        // Convertit l'objet AddressForm en une instance de l'entité Address
        studio.setAddress( this.address.toEntity() );

        return studio; // Convertit le StudioForm en une entité Studio
    }

}
