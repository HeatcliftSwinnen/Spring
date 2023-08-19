package be.technifutur.spring.demo.services.impl;

import be.technifutur.spring.demo.exceptions.DuplicateUniqueIdException;
import be.technifutur.spring.demo.models.JeuxVideo;
import be.technifutur.spring.demo.services.JeuxVideoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class JeuxVideoServiceImpl implements JeuxVideoService {

    private final List<JeuxVideo> jeuxVideoList = new ArrayList<>();

    @Override
    //Add
    public void addJeuxVideo(JeuxVideo toAdd) {
        // On récupére l'id du jeux qu'on ajoute
        int idFromAdd = toAdd.getUniqueId();
        // On vérifie que idFromAdd n'est pas présent dans la liste en la parcourant avec un stream
        boolean exist = jeuxVideoList.stream()
                .anyMatch(jeu -> jeu.getUniqueId() == idFromAdd );
        if (!exist) {
            //si l'unique id n'est pas déja présent on ajoute le jeux à la liste de jeux
            jeuxVideoList.add(toAdd);
        } else {
            //sinon on lance une exception
            throw new DuplicateUniqueIdException("Un jeu avec cet Id existe déjà");
        }
    }

    @Override
    public void deleteJeuxVideo(int id) {
        // Utilise la méthode removeIf pour chercher et supprimer un jeu vidéo ayant l'ID spécifié
        // Si un jeu vidéo avec l'ID donné est trouvé et supprimé, exist sera true
        boolean exist = jeuxVideoList.removeIf(jeu -> jeu.getUniqueId() == id);

        // Si exist est false, cela signifie que le jeu vidéo n'a pas été trouvé et supprimé
        // Dans ce cas, nous lançons une exception pour indiquer que le jeu vidéo n'a pas été trouvé
        if (!exist) {
            throw new NoSuchElementException("Jeu vidéo non trouvé pour l'ID spécifié");
        }
    }

    @Override
    public JeuxVideo getJeuVideo(int id) {
        JeuxVideo jeuVideo = jeuxVideoList.stream()
                .filter(jeu -> jeu.getUniqueId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("ID de jeu vidéo invalide"));
        return jeuVideo;
    }

    @Override
    public List<JeuxVideo> getAll() {
        return new ArrayList<>(jeuxVideoList);
    }

    @Override
    public void modifyAll(int id, JeuxVideo updatedJeuxVideo) {
        JeuxVideo jeuxVideo = jeuxVideoList.stream().filter(jeu ->jeu.getUniqueId()==id).findFirst().orElseThrow(()->new NoSuchElementException("ID de jeu vidéo invalide"));

        jeuxVideo.setName(updatedJeuxVideo.getName());
        jeuxVideo.setGenre(updatedJeuxVideo.getGenre());
        jeuxVideo.setDateDeSortie(updatedJeuxVideo.getDateDeSortie());
        jeuxVideo.setNomStudio(updatedJeuxVideo.getNomStudio());
        jeuxVideo.setPrix(updatedJeuxVideo.getPrix());
        jeuxVideo.setPlatform(updatedJeuxVideo.getPlatform());
    }

    @Override
    public void modifyPrice(int id, double newPrice) {
    JeuxVideo jeuxVideo = jeuxVideoList.stream().filter(jeu -> jeu.getUniqueId()==id ).findFirst().orElseThrow(()-> new NoSuchElementException("\"ID de jeu vidéo invalide\""));
    }

    @Override
    public void addPlatform(int id, String platformToAdd) {
        JeuxVideo jeuVideo = jeuxVideoList.stream()
                .filter(jeu -> jeu.getUniqueId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("ID de jeu vidéo invalide"));

        List<String> platforms = jeuVideo.getPlatform();
        platforms.add(platformToAdd);
    }


}
