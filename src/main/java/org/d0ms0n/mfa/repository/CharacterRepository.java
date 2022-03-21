package org.d0ms0n.mfa.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.d0ms0n.mfa.model.Character;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharacterRepository implements PanacheMongoRepository<Character> {

    public Character findByName(String name) {
        return find("name", name).firstResult();
    }

    public Character findByIdAndUserId(ObjectId id, String userId) {
        return find("_id = ?1 and userId = ?2", id, userId).firstResult();
    }
}
