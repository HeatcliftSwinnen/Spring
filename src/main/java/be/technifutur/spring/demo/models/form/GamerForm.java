package be.technifutur.spring.demo.models.form;
import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.validation.contraints.EmailStartWithPseudo;
import be.technifutur.spring.demo.validation.contraints.EmailUnique;
import be.technifutur.spring.demo.validation.contraints.TimesAgo;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@EmailStartWithPseudo
public class GamerForm {

    @NotBlank
    @Size(min = 6, max = 20)
    private String pseudo; // Pseudo du joueur, ne doit pas être vide et doit avoir une longueur entre 6 et 20 caractères

    @NotBlank
    @Email
    @EmailUnique
    private String email; // Adresse e-mail du joueur, ne doit pas être vide, doit être un format d'e-mail valide et doit être unique

    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[!=@#|$%^&*()_+{}\\\\[\\\\]:;<>,.?~\\\\-]).*(?=.*[A-Z]).*(?=.*[0-9]).*$")
    private String password; // Mot de passe du joueur, ne doit pas être vide, doit contenir au moins 6 caractères, une majuscule, un caractère spécial et un nombre

    @NotNull
    @TimesAgo(message = "should be at least 12 years old") // Vérifie que le joueur a au moins 12 ans
    private LocalDate birthdate; // Date de naissance du joueur, ne doit pas être nulle et doit respecter les contraintes définies

    public Gamer toEntity(){
        Gamer gamer = new Gamer();
        gamer.setPseudo( pseudo );
        gamer.setEmail( email );
        gamer.setPassword( password );
        gamer.setBirthdate( birthdate );
        return gamer; // Convertit le GamerForm en une entité Gamer
    }

}
