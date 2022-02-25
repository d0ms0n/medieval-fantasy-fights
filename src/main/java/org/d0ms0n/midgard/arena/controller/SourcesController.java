package org.d0ms0n.midgard.arena.controller;

import org.d0ms0n.midgard.arena.model.helper.SkillType;
import org.d0ms0n.midgard.arena.model.helper.WeaponType;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("sources")
public class SourcesController {

    @GET
    @Path("/skillTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SkillType> getSkillTypes() {
        return Arrays.stream(SkillType.values()).collect(Collectors.toList());
    }

    @GET
    @Path("/weaponTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeaponType> getWeaponTypes() {
        return Arrays.stream(WeaponType.values()).collect(Collectors.toList());
    }
}
