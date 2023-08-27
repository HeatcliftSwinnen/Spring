package be.technifutur.spring.demo.repository;

import be.technifutur.spring.demo.models.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

// Cette interface définit le repository pour l'entité "Game"
public interface GameRepository extends JpaRepository<Game, Long> {
}
