package org.d0ms0n.mfa.arena.model;

import org.d0ms0n.mfa.arena.game.Dice;

public class Weapon extends Item {
    int magicalAttackBonus;
    int magicalDamageBonus;
    int magicalDefenseBonus;
    RawWeapon rawWeapon;
    boolean equipped;

    public Weapon(String name, String description, float weight, float value, RawWeapon rawWeapon, boolean equipped) {
        super(name, description, weight, value);
        this.equipped = equipped;
        this.rawWeapon = rawWeapon;
    }

    public Weapon() {
    }

    public int getMagicalAttackBonus() {
        return magicalAttackBonus;
    }

    public void setMagicalAttackBonus(int magicalAttackBonus) {
        this.magicalAttackBonus = magicalAttackBonus;
    }

    public int getMagicalDamageBonus() {
        return magicalDamageBonus;
    }

    public void setMagicalDamageBonus(int magicalDamageBonus) {
        this.magicalDamageBonus = magicalDamageBonus;
    }

    public int getMagicalDefenseBonus() {
        return magicalDefenseBonus;
    }

    public void setMagicalDefenseBonus(int magicalDefenseBonus) {
        this.magicalDefenseBonus = magicalDefenseBonus;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public RawWeapon getRawWeapon() {
        return rawWeapon;
    }

    public void setRawWeapon(RawWeapon rawWeapon) {
        this.rawWeapon = rawWeapon;
    }

    public int doDamage() {
        int damage = 0;
        for (int i = 0; i < getRawWeapon().getDiceCnt(); i++) {
            damage += Dice.roll(getRawWeapon().getDiceType());
        }
        damage += getRawWeapon().getStaticDamage();
        damage += getMagicalDamageBonus();

        return Math.max(damage, 0);
    }
}
