package be.technifutur.spring.demo.validation.validators;

import be.technifutur.spring.demo.models.entity.Genre;
import be.technifutur.spring.demo.validation.contraints.ExcludeGenre;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;

public class ExcludeGenreValidator implements ConstraintValidator<ExcludeGenre, Collection<Genre>> {

    private Genre[] excludedGenres;

    @Override
    public void initialize(ExcludeGenre constraintAnnotation) {
        excludedGenres = constraintAnnotation.excluded();
    }

    @Override
    public boolean isValid(Collection<Genre> genres, ConstraintValidatorContext context) {
        return Arrays.stream( excludedGenres )
                .noneMatch( genres::contains );
    }
}
