package be.technifutur.spring.demo.service;

import be.technifutur.spring.demo.models.entity.Gamer;


public interface GamerService extends CrudService<Gamer, Long> {

    void addGame( Long gamerId, Long gameId );

    boolean isEmailTaken(String email);
    boolean isPseudoTaken(String pseudo);

}
