package org.d0ms0n.mfa.arena.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.d0ms0n.mfa.arena.model.RawSkill;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RawSkillRepository implements PanacheMongoRepository<RawSkill> {

    public RawSkill findByName(String name) {
        return find("name", name).firstResult();
    }
}
