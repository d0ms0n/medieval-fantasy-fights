package org.d0ms0n.midgard.arena.game;

import org.d0ms0n.midgard.arena.model.Squad;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class Arena {
    private String name;
    private List<Squad> squads;
    private Combat combat;

    @Inject
    public Arena(Combat combat) {
        this.combat = combat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }

    public Combat getGame() {
        return combat;
    }

    public void setGame(Combat combat) {
        this.combat = combat;
    }
}
