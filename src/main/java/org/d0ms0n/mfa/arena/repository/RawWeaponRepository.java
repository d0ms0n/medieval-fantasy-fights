package org.d0ms0n.mfa.arena.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.d0ms0n.mfa.arena.model.RawWeapon;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RawWeaponRepository implements PanacheMongoRepository<RawWeapon> {

    public RawWeapon findByWeaponName(String weaponName) {
        return find("weaponName", weaponName).firstResult();
    }
}
