package be.technifutur.spring.demo.service;

import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;
import java.util.List;
import java.util.Set;

// Cette interface définit les méthodes du service lié à l'entité "Game"
public interface GameService {

    // Ajouter un jeu et renvoyer l'identifiant généré
    long addGame(Game game);

    // Supprimer un jeu en fonction de son identifiant et renvoyer l'entité supprimée
    Game removeGame(long id);

    // Obtenir un jeu en fonction de son identifiant
    Game getGame(long id);

    // Obtenir la liste de tous les jeux avec des filtres de prix
    List<Game> getAllGames(Double minPrice, Double maxPrice);

    // Mettre à jour un jeu existant et renvoyer l'entité mise à jour
    Game updateGame(long id, Game game);

    // Mettre à jour le prix d'un jeu et renvoyer l'entité mise à jour
    Game updatePrice(long id, double price);

    // Ajouter des plates-formes à un jeu existant et renvoyer l'entité mise à jour
    Game addPlatform(long id, Set<Platform> platforms);

}
