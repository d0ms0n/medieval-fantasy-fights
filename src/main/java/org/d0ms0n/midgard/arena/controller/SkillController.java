package org.d0ms0n.midgard.arena.controller;

import org.d0ms0n.midgard.arena.model.RawSkill;
import org.d0ms0n.midgard.arena.repository.RawSkillRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("skills")
public class SkillController {

    @Inject
    RawSkillRepository skillRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RawSkill> getSkills() {
        return skillRepository.findAll().stream().collect(Collectors.toList());
    }
}
