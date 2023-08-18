package be.technifutur.spring.demo.services.impl;

import be.technifutur.spring.demo.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final List<String> messages = new ArrayList<>();

    @Override
    public String getLastMessage() {
        if(messages.isEmpty())
            return null;
        return messages.get(messages.size()-1);
    }

    @Override
    public List<String> getMessages() {
        return List.copyOf(messages);
    }

    @Override
    public String getMessage(int index) {
        return messages.get(index);
    }

    @Override
    public void addMessage(String toAdd) {
        messages.add(toAdd);
    }

    @Override
    public void deleteMEssage(int index) {
        messages.remove(index);
    }

    @Override
    public void changeMessage(int index, String replacement) {
        messages.set(index, replacement);
    }
}
