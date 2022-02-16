package org.d0ms0n.midgard.arena.service;

import org.d0ms0n.midgard.arena.game.Arena;
import org.d0ms0n.midgard.arena.model.Character;
import org.d0ms0n.midgard.arena.model.*;
import org.d0ms0n.midgard.arena.model.helper.ArmourType;
import org.d0ms0n.midgard.arena.model.helper.SkillType;
import org.d0ms0n.midgard.arena.model.helper.WeaponType;
import org.d0ms0n.midgard.arena.repository.RawSkillRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ArenaService {

    public static Logger log = Logger.getLogger(ArenaService.class);

    @Inject
    Arena arena;

    @Inject
    RawSkillRepository skillRepository;

    public Arena create(String arenaName) {

        skillRepository.findAll().stream().forEach(skill -> {
            log.info(skill.getName());
        });

        List<Squad> squads = new ArrayList<>();
        Squad adventurer = new Squad("Friends", new ArrayList<>());
        Squad foes = new Squad("Foes", new ArrayList<>());

        Attributes attributes = new Attributes(90, 90, 90, 90, 90, 90, 90, 90, 90);
        adventurer.setMembers(Collections.singletonList(
                new Character("Adventurer", adventurer.getSquadId(),
                        18, 18,
                        18, 18,
                        11, attributes, new String[]{},
                        Arrays.asList(
                                new Skill(WeaponType.ONE_HANDED_SWORD.getName(), SkillType.WEAPON, 10),
                                new Skill(WeaponType.PARRYING_WEAPON.getName(), SkillType.WEAPON, 2)),
                        Arrays.asList(
                                new Weapon(1, 6, 1, "Langschwert", WeaponType.ONE_HANDED_SWORD, true),
                                new ParryingWeapon(1, 6, -1, "Parierdolch", 2, WeaponType.PARRYING_WEAPON, true),
                                new Armour(true, ArmourType.CHAIN)
                        ))
        ));

        foes.setMembers(Collections.singletonList(
                        new Character("Orc", foes.getSquadId(),
                                14, 14,
                                18, 18,
                                11, attributes, new String[]{},
                                Arrays.asList(
                                        new Skill(WeaponType.ONE_HANDED_STRIKING_WEAPON.getName(), SkillType.WEAPON, 8),
                                        new Skill(WeaponType.SHIELD.getName(), SkillType.WEAPON, 2)),
                                Arrays.asList(
                                        new Weapon(1, 6, 1, "Streitaxt", WeaponType.ONE_HANDED_STRIKING_WEAPON, true),
                                        new ParryingWeapon(0, 0, 0, "Kleiner Schild", 1, WeaponType.SHIELD, true),
                                        new Armour(true, ArmourType.LEATHER)
                                ))
                )
        );

        squads.add(adventurer);
        squads.add(foes);
        arena.setName("Arena");
        arena.setSquads(squads);
        arena.getGame().start(squads);
        return arena;
    }
}
