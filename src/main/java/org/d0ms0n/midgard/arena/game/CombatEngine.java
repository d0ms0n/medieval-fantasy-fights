package org.d0ms0n.midgard.arena.game;

import org.d0ms0n.midgard.arena.model.Character;
import org.d0ms0n.midgard.arena.model.ParryingWeapon;
import org.d0ms0n.midgard.arena.model.Squad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class CombatEngine {
    int roundCnt;
    List<Character> fighters;
    boolean end = false;
    public static Logger log = LoggerFactory.getLogger(CombatEngine.class);

    public CombatEngine() {
    }

    public int getRoundCnt() {
        return roundCnt;
    }

    public void start(List<Squad> squads) {
        fighters = new ArrayList<>();
        squads.forEach(squad -> fighters.addAll(squad.getMembers()));
        Collections.sort(fighters);

        while (!end) {
            roundCnt++;
            round();
        }
    }

    public void round() {
        log.info("###############################");
        log.info("Start Round {}", roundCnt);
        fighters.forEach(f -> {
            log.info("{} from {} starts his round with {}/{} LP {}/{} AP", f.getName(), f.getSquadId(),
                    f.getLifePoints(), f.getLifePointsMax(), f.getStaminaPoints(), f.getStaminaPointsMax());
        });


        fighters.forEach(fighter -> {
            if (fighter.getLifePoints() > 3) {
                Character opponent = findOpponent(fighter.getSquadId());
                attack(fighter, opponent);
            }
        });

        if (fighters.stream()
                .filter(fighter -> fighter.getLifePoints() > 3)
                .map(Character::getSquadId)
                .distinct()
                .count() < 2) {
            end = true;
        }
    }

    private Character findOpponent(long squadId) {
        return fighters.stream().filter(fighter -> fighter.getSquadId() != squadId)
                .findFirst().get();
    }

    private int calcAbsoluteDamage(Character attacker) {
        return attacker.getAttackWeapon().doDamage() + attacker.getDamageBonus();
    }

    private int calcLpDamage(int absoluteDamage, Character defender) {
        return Math.max(absoluteDamage - defender.getArmourClass(), 0);
    }

    private int calcApDamage(int absoluteDamage, Character defender, boolean heavyDamage) {
        ParryingWeapon parryingWeapon = defender.getParryingWeapon();
        if (parryingWeapon != null && absoluteDamage >= parryingWeapon.getStaminaPointsPreserving() && !heavyDamage) {
            absoluteDamage -= parryingWeapon.getStaminaPointsPreserving();
            log.info("{}'s {} saves {} AP", defender.getName(), parryingWeapon.getName(), parryingWeapon.getStaminaPointsPreserving());
        }
        return Math.max(absoluteDamage, 0);
    }

    private int calcAttackValue(Character attacker, Character defender) {
        int roll = Dice.r20();
        int attack = attacker.getAttackValue(attacker.getAttackWeapon()) + roll;
        String bonus = "";
        if (isExhausted(attacker)) {
            attack -= 4;
            bonus = "attacker exhausted -> -4 ";
        }
        if (isExhausted(defender)) {
            attack += 4;
            bonus += "defender exhausted -> +4 ";
        }
        if (bonus.isEmpty()) {
            log.info("{} rolls a {} and has a {}", attacker.getName(), roll, attack);
        } else {
            log.info("{} rolls a {} and has a {} ({})", attacker.getName(), roll, attack, bonus);
        }
        return attack;
    }

    private int calcDefenseValue(Character defender) {
        int roll = Dice.r20();
        String bonus = "";
        int defense = defender.getFullDefense() + roll;
        if (isExhausted(defender)) {
            defense -= 4;
            bonus = "defender exhausted -> -4 ";
        }
        if (bonus.isEmpty()) {
            log.info("{} rolls a {} and has a {}", defender.getName(), roll, defense);
        } else {
            log.info("{} rolls a {} and has a {} ({})", defender.getName(), roll, defense, bonus);
        }
        return defense;
    }

    private void doHeavyDamage(int absoluteDamage, Character defender, Character attacker) {
        int lpDamage = calcLpDamage(absoluteDamage, defender);
        int apDamage = calcApDamage(absoluteDamage, defender, true);
        log.info("{} does heavy damage: {} LP / {} AP", attacker.getName(), lpDamage, apDamage);
        defender.setLifePoints(defender.getLifePoints() - lpDamage);
        defender.setStaminaPoints(Math.max(defender.getStaminaPoints() - apDamage, 0));
        if (isExhausted(defender)) {
            log.info("{} is exhausted", defender.getName());
        }
        if (isDead(defender)) {
            log.info("{} is dead", defender.getName());
        }

    }

    private void doLightDamage(int absoluteDamage, Character defender, Character attacker) {
        int apDamage = calcApDamage(absoluteDamage, defender, false);
        log.info("{} does light damage: {} AP", attacker.getName(), apDamage);
        defender.setStaminaPoints(Math.max(defender.getStaminaPoints() - apDamage, 0));
        isExhausted(defender);
    }

    private boolean isExhausted(Character fighter) {
        return fighter.getStaminaPoints() == 0;
    }

    private boolean isDead(Character fighter) {
        return fighter.getLifePoints() <= 0;
    }

    private void attack(Character attacker, Character defender) {
        log.info("{} attacks {} with his {}", attacker.getName(), defender.getName(), attacker.getAttackWeapon().getName());
        int attack = calcAttackValue(attacker, defender);

        if (attack >= 20) {
            int absoluteDamage = calcAbsoluteDamage(attacker);
            int defense = calcDefenseValue(defender);
            log.info("attack succeeded with {} and does {} damage", attack, absoluteDamage);
            if (defense < attack) {
                doHeavyDamage(absoluteDamage, defender, attacker);
            } else {
                log.info("{} does a parry with {}", defender.getName(), defense);
                doLightDamage(absoluteDamage, defender, attacker);
            }
        } else {
            log.info("attack missed with {}", attack);
        }
    }
}
