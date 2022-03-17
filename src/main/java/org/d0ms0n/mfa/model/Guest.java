package org.d0ms0n.mfa.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "lobby")
public class Guest {
    ObjectId id;
    ObjectId characterId;
    String userId;
    int level;

    public Guest() {
    }

    public Guest(ObjectId characterId, String userId, int level) {
        this.characterId = characterId;
        this.userId = userId;
        this.level = level;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCharacterId() {
        return characterId;
    }

    public void setCharacterId(ObjectId characterId) {
        this.characterId = characterId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
