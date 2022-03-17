package org.d0ms0n.mfa.controller;

import org.bson.types.ObjectId;
import org.d0ms0n.mfa.model.Character;
import org.d0ms0n.mfa.model.Guest;
import org.d0ms0n.mfa.repository.CharacterRepository;
import org.d0ms0n.mfa.repository.LobbyRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/game/{userId}")
@ApplicationScoped
public class GameSocket {

    @Inject
    LobbyRepository lobbyRepository;

    @Inject
    CharacterRepository characterRepository;

    Map<String, Session> sessions = new ConcurrentHashMap<>();
    List<String> allowedCommands = Arrays.asList("join", "start");

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        sessions.put(userId, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        sessions.remove(userId);
        broadcast("User " + userId + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable throwable) {
        sessions.remove(userId);
        broadcast("User " + userId + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId) {
        String[] split = message.toLowerCase().split(":");
        if (split.length == 2 && isCommandValid(split[0]) && isCharacterIdValid(split[1])) {
            String command = split[0];
            String charId = split[1];

            if (command.equals("join")) {
                ObjectId characterIdObj = new ObjectId(charId);
                Character character = characterRepository.findById(characterIdObj);
                if (character != null) {
                    lobbyRepository.persist(new Guest(characterIdObj, userId, character.getLevel()));
                    broadcast(">> " + userId + ":" + characterIdObj + " waits for a fight");
                }
                lobbyRepository.persist(new Guest(characterIdObj, userId, 1));
            }
        }
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

    private boolean isCommandValid(String command) {
        if (command != null && !command.isEmpty() && !command.isBlank()) {
            return allowedCommands.contains(command);
        }
        return false;
    }

    private boolean isCharacterIdValid(String characterId) {
        if (characterId != null && !characterId.isEmpty() && !characterId.isBlank()) {
            return true;
        }
        return false;
    }
}