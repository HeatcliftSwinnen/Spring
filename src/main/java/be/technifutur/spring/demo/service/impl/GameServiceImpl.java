package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Platform;
import be.technifutur.spring.demo.repository.GameRepository;
import be.technifutur.spring.demo.service.GameService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public long addGame(Game game) {
        // Ajoute un jeu en le sauvegardant dans la base de données
        game = gameRepository.save( game );
        return game.getId();
    }

    @Override
    public Game removeGame(long id) {
        // Supprime un jeu en fonction de son ID
        Game game = getGame( id );
        gameRepository.delete( game );
        return game;
    }

    @Override
    public Game getGame(long id) {
        // Récupère un jeu en fonction de son ID
        return gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Game.class));
    }

    @Override
    public List<Game> getAllGames(Double minPrice, Double maxPrice) {
        // Récupère la liste de tous les jeux en filtrant par prix minimum et maximum
        return gameRepository.findAll().stream()
                .filter(game -> minPrice == null || game.getPrice() >= minPrice)
                .filter(game -> maxPrice == null || game.getPrice() <= maxPrice)
                .toList();
    }

    @Override
    public Game updateGame(long id, Game game) {
        // Met à jour les informations d'un jeu en fonction de son ID
        game.setId(id);
        return gameRepository.save( game );
    }

    @Override
    public Game updatePrice(long id, double price) {
        // Met à jour le prix d'un jeu en fonction de son ID
        if( price < 0 )
            throw new IllegalArgumentException("price should be positive or 0");

        Game game = getGame(id);
        game.setPrice( price );
        return gameRepository.save( game );
    }

    @Override
    public Game addPlatform(long id, Set<Platform> platforms) {
        // Ajoute des plates-formes à un jeu en fonction de son ID
        Assert.notNull(platforms, "platforms shoud not be null");

        Game game = getGame(id);
        game.getPlatforms().addAll(platforms); // TODO pas ajouter une plateforme existante
        return gameRepository.save( game );
    }
}
