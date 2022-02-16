package org.d0ms0n.midgard.arena.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.d0ms0n.midgard.arena.model.Weapon;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeaponRepository implements PanacheMongoRepository<Weapon> {

    public Weapon findByName(String name) {
        return find("name", name).firstResult();
    }
}
