package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceAlreadyLinkedException;
import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.exceptions.UniqueViolationException;
import be.technifutur.spring.demo.models.entity.Game;
import be.technifutur.spring.demo.models.entity.Gamer;
import be.technifutur.spring.demo.repository.GamerRepository;
import be.technifutur.spring.demo.service.GameService;
import be.technifutur.spring.demo.service.GamerService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class GamerServiceImpl implements GamerService {

    private final GamerRepository gamerRepository;
    private final GameService gameService;

    public GamerServiceImpl(GamerRepository gamerRepository, GameService gameService) {
        this.gamerRepository = gamerRepository;
        this.gameService = gameService;
    }

    @Override
    public Long add(Gamer gamer) {
        gamer.setId(null);

        List<String> fieldUniqueErrors = new LinkedList<>();
        if( gamerRepository.existsByPseudo( gamer.getPseudo() ) )
            fieldUniqueErrors.add("pseudo");

        if( !fieldUniqueErrors.isEmpty() )
            throw new UniqueViolationException(fieldUniqueErrors);

        return gamerRepository.save( gamer ).getId();
    }

    @Override
    public List<Gamer> getAll() {
        // Récupère la liste de tous les joueurs actifs de la base de données
        return gamerRepository.findAll().stream()
                .filter( Gamer::isActive )
                .toList();
    }

    @Override
    public Gamer getOne(Long id) {
        // Récupère un joueur spécifique en fonction de son ID
        return gamerRepository.findById(id)
                .filter( Gamer::isActive )
                .orElseThrow(() -> new ResourceNotFoundException(id, Gamer.class));
    }

    @Override
    public void update(Long id, Gamer gamer) {
        // Met à jour les informations d'un joueur spécifique en fonction de son ID
        Gamer entity = getOne( id );

        entity.setPseudo( gamer.getPseudo() );
        entity.setEmail( gamer.getEmail() );
        entity.setPassword( gamer.getPassword() );
        entity.setBirthdate( gamer.getBirthdate() );

        gamerRepository.save( entity );
    }

    @Override
    public void delete(Long id) {
        // Désactive un joueur spécifique en fonction de son ID
        Gamer gamer = getOne( id );
        gamer.setActive( false );
        gamerRepository.save( gamer );
    }

    @Override
    public void addGame(Long gamerId, Long gameId) {
        // Ajoute un jeu à la liste de jeux joués par un joueur
        Gamer gamer = getOne( gamerId );
        Game game = gameService.getGame( gameId );

        // Vérifie si le jeu est déjà présent dans la liste des jeux joués par le joueur
        boolean gameAlreadyPresent = gamer.getGamesPlayed().stream()
                .anyMatch( gamePlayed -> Objects.equals(gamePlayed.getId(), game.getId()) );

        if( gameAlreadyPresent )
            throw new ResourceAlreadyLinkedException(Gamer.class, gamerId, Game.class, gameId);

        // Ajoute le jeu à la liste et sauvegarde le joueur
        gamer.getGamesPlayed().add( game );
        gamerRepository.save( gamer );
    }

    @Override
    public boolean isEmailTaken(String email) {
        // Vérifie si une adresse e-mail est déjà utilisée par un autre joueur
        return gamerRepository.existsByEmail(email);
    }

    @Override
    public boolean isPseudoTaken(String pseudo) {
        // Vérifie si un pseudo est déjà utilisé par un autre joueur
        return gamerRepository.existsByPseudo(pseudo);
    }

    private String generateRandomPwd(){
        return "change_me";
        // Vous pouvez également décommenter le code ci-dessous pour générer un mot de passe aléatoire

        // final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // SecureRandom secureRandom = new SecureRandom();
        //
        // return IntStream.range(0, len)
        //         .map(i -> secureRandom.nextInt(chars.length()))
        //         .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
        //         .collect(Collectors.joining());
    }

}
