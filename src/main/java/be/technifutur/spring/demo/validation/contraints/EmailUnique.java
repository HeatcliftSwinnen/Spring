package be.technifutur.spring.demo.validation.contraints;

import be.technifutur.spring.demo.validation.validators.EmailUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailUniqueValidator.class)
public @interface EmailUnique {
    String message() default "email already used";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
