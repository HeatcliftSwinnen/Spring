package be.technifutur.spring.demo.repository;

import be.technifutur.spring.demo.models.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

// Cette interface définit le repository pour l'entité "Studio"
public interface StudioRepository extends JpaRepository<Studio, Long> {
}
