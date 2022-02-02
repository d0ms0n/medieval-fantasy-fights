package org.d0ms0n.midgard.arena.game;

import org.d0ms0n.midgard.arena.model.ParryingWeapon;
import org.d0ms0n.midgard.arena.model.Weapon;

import java.util.List;

public class Fighter implements Comparable<Fighter> {
    private String name;
    private int maxLp;
    private int lp;
    private int maxAp;
    private int ap;
    private int attack;
    private int defence;
    private int armourClass;
    private int agility;
    private final long squadId;
    private Weapon weapon;
    private ParryingWeapon parryingWeapon;
    private List<Effect> effects;

    public Fighter(String name, int maxLp, int lp, int maxAp, int ap, int attack, int defence, int armourClass, int agility, long squadId, Weapon weapon, ParryingWeapon parryingWeapon) {
        this.name = name;
        this.maxLp = maxLp;
        this.lp = lp;
        this.maxAp = maxAp;
        this.ap = ap;
        this.attack = attack;
        this.defence = defence;
        this.armourClass = armourClass;
        this.agility = agility;
        this.squadId = squadId;
        this.weapon = weapon;
        this.parryingWeapon = parryingWeapon;
    }

    public ParryingWeapon getParryingWeapon() {
        return parryingWeapon;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public long getSquadId() {
        return squadId;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLp() {
        return maxLp;
    }

    public void setMaxLp(int maxLp) {
        this.maxLp = maxLp;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public int getMaxAp() {
        return maxAp;
    }

    public void setMaxAp(int maxAp) {
        this.maxAp = maxAp;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    @Override
    public int compareTo(Fighter fighter) {
        return fighter.getAgility() - agility;
    }
}
