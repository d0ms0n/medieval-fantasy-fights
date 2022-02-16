package org.d0ms0n.midgard.arena.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.d0ms0n.midgard.arena.model.RawSkill;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RawSkillRepository implements PanacheMongoRepository<RawSkill> {
}
