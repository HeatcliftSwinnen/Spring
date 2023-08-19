package be.technifutur.spring.demo;


import be.technifutur.spring.demo.models.JeuxVideo;
import be.technifutur.spring.demo.services.JeuxVideoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {


		// Lancement de l'application Spring et obtention du contexte d'application
		ApplicationContext applicationContext = SpringApplication.run(SpringDemoApplication.class, args);

		// Récupération d'une instance du service JeuxVideoService à partir du contexte d'application
		JeuxVideoService jeuxVideoService = applicationContext.getBean(JeuxVideoService.class);



		jeuxVideoService.addJeuxVideo(new JeuxVideo(
				1,
				"Fantasy Quest",
				List.of("RPG", "Fantasy"),
				LocalDate.of(2022, 3, 15),
				"Mythic Studios",
				49.99,
				List.of("PC", "PlayStation 5", "Xbox Series X")
		));

		jeuxVideoService.addJeuxVideo(new JeuxVideo(
				2,
				"Galactic Odyssey",
				List.of("Sci-Fi", "Action", "Adventure"),
				LocalDate.of(2023, 7, 28),
				"Stellar Games",
				59.99,
				List.of("PC", "PlayStation 5", "Xbox Series X", "Nintendo Switch")
		));

		jeuxVideoService.addJeuxVideo(new JeuxVideo(
				3,
				"Mystic Runes",
				List.of("Adventure", "Puzzle"),
				LocalDate.of(2021, 11, 10),
				"Enigma Studios",
				39.99,
				List.of("PC", "PlayStation 4", "Xbox One")
		));

	}


}
