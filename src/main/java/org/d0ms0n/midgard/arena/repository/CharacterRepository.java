package org.d0ms0n.midgard.arena.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharacterRepository implements PanacheMongoRepository<Character> {

    public Character findByName(String name) {
        return find("name", name).firstResult();
    }
}
