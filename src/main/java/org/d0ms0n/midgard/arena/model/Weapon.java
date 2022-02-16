package org.d0ms0n.midgard.arena.model;

import org.d0ms0n.midgard.arena.game.Dice;
import org.d0ms0n.midgard.arena.model.helper.WeaponType;

public class Weapon extends Item {
    int diceCnt;
    int diceType;
    int staticDamage;
    String name;
    int magicalAttackBonus;
    int magicalDamageBonus;
    int magicalDefenseBonus;
    WeaponType weaponType;
    boolean equipped;

    public Weapon(int diceCnt, int diceType, int staticDamage, String name, WeaponType weaponType, boolean equipped) {
        this.diceCnt = diceCnt;
        this.diceType = diceType;
        this.staticDamage = staticDamage;
        this.name = name;
        this.weaponType = weaponType;
        this.equipped = equipped;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
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

    public void setDiceCnt(int diceCnt) {
        this.diceCnt = diceCnt;
    }

    public void setDiceType(int diceType) {
        this.diceType = diceType;
    }

    public void setStaticDamage(int staticDamage) {
        this.staticDamage = staticDamage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiceCnt() {
        return diceCnt;
    }

    public int getDiceType() {
        return diceType;
    }

    public int getStaticDamage() {
        return staticDamage;
    }

    public String getName() {
        return name;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public int doDamage() {
        int damage = 0;
        for (int i = 0; i < getDiceCnt(); i++) {
            damage += Dice.roll(getDiceType());
        }
        damage += getStaticDamage();
        damage += getMagicalDamageBonus();

        return Math.max(damage, 0);
    }
}
