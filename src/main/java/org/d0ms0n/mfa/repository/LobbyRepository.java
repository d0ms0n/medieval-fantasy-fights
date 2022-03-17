package org.d0ms0n.mfa.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.d0ms0n.mfa.model.Guest;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LobbyRepository implements PanacheMongoRepository<Guest> {

    public Guest findByCharacterId(ObjectId characterId) {
        return find("characterId", characterId).firstResult();
    }

    public Guest findByUserId(ObjectId userId) {
        return find("userId", userId).firstResult();
    }
}
