package org.d0ms0n.mfa.controller;

import org.d0ms0n.mfa.game.Arena;
import org.d0ms0n.mfa.service.ArenaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/arena")
public class ArenaController {

    @Inject
    ArenaService arena;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Arena hello() {
        return arena.create("TestArena");
    }
}