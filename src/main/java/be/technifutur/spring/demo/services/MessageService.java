package be.technifutur.spring.demo.services;

import java.util.List;

public interface MessageService {

    String getLastMessage();
    List<String> getMessages();
    String getMessage(int index);
    void addMessage(String toAdd);
    void deleteMEssage(int index);
    void changeMessage(int index,String replacement);
}
