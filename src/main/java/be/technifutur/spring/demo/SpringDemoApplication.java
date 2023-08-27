package be.technifutur.spring.demo;

import be.technifutur.spring.demo.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {


		// Lancement de l'application Spring et obtention du contexte d'application
		ApplicationContext applicationContext = SpringApplication.run(SpringDemoApplication.class, args);
		// Récupération d'une instance du service JeuxVideoService à partir du contexte d'application
		GameService jeuxVideoService = applicationContext.getBean(GameService.class);


	}
}
