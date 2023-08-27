package be.technifutur.spring.demo.validation;

import be.technifutur.spring.demo.validation.validators.EmailStartWithPseudoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailStartWithPseudoValidator.class)
public @interface EmailStartWithPseudo {
    String message() default "email should start with the pseudo"; // Message d'erreur par défaut en cas de validation échouée
    Class<?>[] groups() default {}; // Groupes de validation
    Class<? extends Payload>[] payload() default {}; // Charge utile associée à la validation
}
