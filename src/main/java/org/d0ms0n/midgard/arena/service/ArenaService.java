package org.d0ms0n.midgard.arena.service;

import org.d0ms0n.midgard.arena.game.*;
import org.d0ms0n.midgard.arena.model.ParryingWeapon;
import org.d0ms0n.midgard.arena.model.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ArenaService {

    @Inject
    Arena arena;

    public Arena create(String arenaName) {
        List<Squad> squads = new ArrayList<>();
        Squad adventurer = new Squad("Friends", new ArrayList<>());
        Squad foes = new Squad("Foes", new ArrayList<>());

        adventurer.setMembers(Collections.singletonList(new Fighter("Adventurer",
                18, 18,
                18, 18,
                8, 11,
                2, 90,
                adventurer.getSquadId(), new Weapon(1,6,1, "Longsword"),
                new ParryingWeapon(1, 6, -3, "Parierdolch", 2))));

        foes.setMembers(Collections.singletonList(new Fighter("Orc", 12, 12,
                15,15,
                9, 11,
                2, 80, foes.getSquadId(), new Weapon(1,6,1,"Axe"), new ParryingWeapon(0, 0, 0, "Kleiner Schild", 1))));
        squads.add(adventurer);
        squads.add(foes);
        arena.setName("Arena");
        arena.setSquads(squads);
        arena.getGame().start(squads);
        return arena;
    }
}
