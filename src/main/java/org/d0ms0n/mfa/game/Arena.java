package org.d0ms0n.mfa.game;

import org.d0ms0n.mfa.model.Squad;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class Arena {
    private String name;
    private List<Squad> squads;
    private CombatEngine combatEngine;

    @Inject
    public Arena(CombatEngine combatEngine) {
        this.combatEngine = combatEngine;
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

    public CombatEngine getGame() {
        return combatEngine;
    }

    public void setGame(CombatEngine combatEngine) {
        this.combatEngine = combatEngine;
    }
}
