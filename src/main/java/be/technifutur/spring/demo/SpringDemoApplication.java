package be.technifutur.spring.demo;


import be.technifutur.spring.demo.models.JeuxVideo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {

		ApplicationContext ctxt= SpringApplication.run(SpringDemoApplication.class, args);
		//Scanner scanner = ctxt.getBean(Scanner.class);

		 List<JeuxVideo> ListJeux = new ArrayList<>();

		ListJeux.add(new JeuxVideo(
				1,
				"Fantasy Quest",
				List.of("RPG", "Fantasy"),
				LocalDate.of(2022, 3, 15),
				"Mythic Studios",
				49.99,
				List.of("PC", "PlayStation 5", "Xbox Series X")
		));

		ListJeux.add(new JeuxVideo(
				2,
				"Galactic Odyssey",
				List.of("Sci-Fi", "Action", "Adventure"),
				LocalDate.of(2023, 7, 28),
				"Stellar Games",
				59.99,
				List.of("PC", "PlayStation 5", "Xbox Series X", "Nintendo Switch")
		));

		ListJeux.add(new JeuxVideo(
				3,
				"Mystic Runes",
				List.of("Adventure", "Puzzle"),
				LocalDate.of(2021, 11, 10),
				"Enigma Studios",
				39.99,
				List.of("PC", "PlayStation 4", "Xbox One")
		));

		ListJeux.add(new JeuxVideo(
				4,
				"Cyber Nexus",
				List.of("Cyberpunk", "Open World", "Shooter"),
				LocalDate.of(2023, 5, 2),
				"Neon Interactive",
				49.99,
				List.of("PC", "PlayStation 5", "Xbox Series X")
		));

	}

}
