package org.d0ms0n.mfa.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.d0ms0n.mfa.model.RawWeapon;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RawWeaponRepository implements PanacheMongoRepository<RawWeapon> {

    public RawWeapon findByWeaponName(String weaponName) {
        return find("name", weaponName).firstResult();
    }
}
