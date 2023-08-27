package be.technifutur.spring.demo.validation.contraints;

import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.validation.validators.ExcludeGenreValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExcludeGenreValidator.class)
public @interface ExcludeGenre {
    String message() default "using forbidden genre";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


    Genre[] excluded();
}
