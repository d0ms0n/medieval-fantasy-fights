package org.d0ms0n.midgard.arena.model;

import org.d0ms0n.midgard.arena.model.helper.ArmourType;

public class Armour extends Item {
    boolean putOn;
    boolean damaged;
    ArmourType armourType;
    int requireStrength;
    int lossOfAgility;
    int lossOfMovement;
    int lossOfDefenseBonus;
    int lossOfAttackBonus;

    public Armour(boolean putOn, ArmourType type) {
        this.putOn = putOn;
        this.armourType = type;
    }

    public boolean isPutOn() {
        return putOn;
    }

    public void setPutOn(boolean putOn) {
        this.putOn = putOn;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public ArmourType getArmourType() {
        return armourType;
    }

    public int getArmourClassBonus() {
        return this.getArmourType().getArmourClassBonus();
    }

    public int getRequireStrength() {
        return requireStrength;
    }

    public void setRequireStrength(int requireStrength) {
        this.requireStrength = requireStrength;
    }

    public int getLossOfAgility() {
        return lossOfAgility;
    }

    public void setLossOfAgility(int lossOfAgility) {
        this.lossOfAgility = lossOfAgility;
    }

    public int getLossOfMovement() {
        return lossOfMovement;
    }

    public void setLossOfMovement(int lossOfMovement) {
        this.lossOfMovement = lossOfMovement;
    }

    public int getLossOfDefenseBonus() {
        return lossOfDefenseBonus;
    }

    public void setLossOfDefenseBonus(int lossOfDefenseBonus) {
        this.lossOfDefenseBonus = lossOfDefenseBonus;
    }

    public int getLossOfAttackBonus() {
        return lossOfAttackBonus;
    }

    public void setLossOfAttackBonus(int lossOfAttackBonus) {
        this.lossOfAttackBonus = lossOfAttackBonus;
    }
}
