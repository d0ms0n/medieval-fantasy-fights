package org.d0ms0n.midgard.arena.game;

import org.d0ms0n.midgard.arena.model.ParryingWeapon;
import org.d0ms0n.midgard.arena.model.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class Game {
    int roundCnt;
    List<Fighter> fighters;
    boolean end = false;
    public static Logger log = LoggerFactory.getLogger(Game.class);

    public Game() {
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
                    f.getLp(), f.getMaxLp(), f.getAp(), f.getMaxAp());
        });


        fighters.forEach(fighter -> {
            if (fighter.getLp() > 3) {
                Fighter opponent = findOpponent(fighter.getSquadId());
                attack(fighter, opponent);
            }
        });

        if (fighters.stream()
                .filter(fighter -> fighter.getLp() > 3)
                .map(Fighter::getSquadId)
                .distinct()
                .count() < 2) {
            end = true;
        }
    }

    private Fighter findOpponent(long squadId) {
        return fighters.stream().filter(fighter -> fighter.getSquadId() != squadId)
                .findFirst().get();
    }

    private int calcAbsoluteDamage(Fighter attacker) {
        int damage = 0;
        Weapon attackWeapon = attacker.getWeapon();
        for (int i = 0; i < attackWeapon.getDiceCnt(); i++) {
            damage += Dice.roll(attackWeapon.getDiceType());
        }
        damage += attackWeapon.getStaticDamage();
        return Math.max(damage, 0);
    }

    private int calcLpDamage(int absoluteDamage, Fighter defender) {
        return Math.max(absoluteDamage - defender.getArmourClass(), 0);
    }

    private int calcApDamage(int absoluteDamage, Fighter defender, boolean heavyDamage) {
        ParryingWeapon parryingWeapon = defender.getParryingWeapon();
        if (parryingWeapon != null && absoluteDamage >= parryingWeapon.getApPreserving() && !heavyDamage) {
            absoluteDamage -= parryingWeapon.getApPreserving();
            log.info("{}'s {} saves {} AP", defender.getName(), parryingWeapon.getName(), parryingWeapon.getApPreserving());
        }
        return Math.max(absoluteDamage, 0);
    }

    private int calcAttackValue(Fighter attacker, Fighter defender) {
        int roll = Dice.r20();
        int attack = attacker.getAttack() + roll;
        String bonus ="";
        if (isExhausted(attacker)) {
            attack -= 4;
            bonus = "attacker exhausted -> -4 ";
        }
        if (isExhausted(defender)) {
            attack += 4;
            bonus += "defender exhausted -> +4 ";
        }
        if (bonus.isEmpty()){
            log.info("{} rolls a {} and has a {}", attacker.getName(), roll, attack);
        } else{
            log.info("{} rolls a {} and has a {} ({})", attacker.getName(), roll, attack, bonus);
        }
        return attack;
    }

    private int calcDefenseValue(Fighter defender) {
        int roll = Dice.r20();
        String bonus = "";
        int defense = defender.getDefence() + roll;
        if (isExhausted(defender)) {
            defense -= 4;
            bonus = "defender exhausted -> -4 ";
        }
        if (bonus.isEmpty()){
            log.info("{} rolls a {} and has a {}", defender.getName(), roll, defense);
        } else{
            log.info("{} rolls a {} and has a {} ({})", defender.getName(), roll, defense, bonus);
        }
        return defense;
    }

    private void doHeavyDamage(int absoluteDamage, Fighter defender, Fighter attacker) {
        int lpDamage = calcLpDamage(absoluteDamage, defender);
        int apDamage = calcApDamage(absoluteDamage, defender, true);
        log.info("{} does heavy damage: {} LP / {} AP", attacker.getName(), lpDamage, apDamage);
        defender.setLp(defender.getLp() - lpDamage);
        defender.setAp(Math.max(defender.getAp() - apDamage, 0));
        if (isExhausted(defender)) {
            log.info("{} is exhausted", defender.getName());
        }
        if (isDead(defender)) {
            log.info("{} is dead", defender.getName());
        }

    }

    private void doLightDamage(int absoluteDamage, Fighter defender, Fighter attacker) {
        int apDamage = calcApDamage(absoluteDamage, defender, false);
        log.info("{} does light damage: {} AP", attacker.getName(), apDamage);
        defender.setAp(Math.max(defender.getAp() - apDamage, 0));
        isExhausted(defender);
    }

    private boolean isExhausted(Fighter fighter) {
        return fighter.getAp() == 0;
    }

    private boolean isDead(Fighter fighter) {
        return fighter.getLp() <= 0;
    }

    private void attack(Fighter attacker, Fighter defender) {
        log.info("{} attacks {} with his {}", attacker.getName(), defender.getName(), attacker.getWeapon().getName());
        int attack = calcAttackValue(attacker, defender);
        int absoluteDamage = calcAbsoluteDamage(attacker);

        if (attack >= 20) {
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
