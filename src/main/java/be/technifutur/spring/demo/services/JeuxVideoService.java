package be.technifutur.spring.demo.services;

import be.technifutur.spring.demo.models.JeuxVideo;

import java.util.List;

public interface JeuxVideoService {

    void addJeuxVideo(JeuxVideo toAdd);
    void deleteJeuxVideo(int id);
    JeuxVideo getJeuVideo(int id);
    List<JeuxVideo> getAll();
    Void modifyAll(int id);
    Void modify(int id,double newPrice);
    void addPlatform (int id, String platformToAdd);
}
