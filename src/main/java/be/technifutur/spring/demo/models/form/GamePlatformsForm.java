package be.technifutur.spring.demo.models.form;

import be.technifutur.spring.demo.models.entity.Platform;
import lombok.Data;
import java.util.Set;

@Data
public class GamePlatformsForm {

    // Plates-formes sur lesquelles le jeu est disponible
    private Set<Platform> platforms;
}
