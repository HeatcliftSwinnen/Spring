package be.technifutur.spring.demo.repository;

import be.technifutur.spring.demo.models.entity.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GamerRepository extends JpaRepository<Gamer, Long> {

    boolean existsByPseudo(String pseudo);
    boolean existsByEmail(String email);

}
