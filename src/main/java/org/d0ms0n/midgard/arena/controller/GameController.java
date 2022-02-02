package org.d0ms0n.midgard.arena.controller;

import org.d0ms0n.midgard.arena.game.Arena;
import org.d0ms0n.midgard.arena.service.ArenaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/hello")
public class GameController {

    @Inject
    ArenaService arena;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Arena hello() {
        return arena.create("TestArena");
    }
}