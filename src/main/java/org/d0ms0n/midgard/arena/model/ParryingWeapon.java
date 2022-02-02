package org.d0ms0n.midgard.arena.model;

public class ParryingWeapon extends Weapon {

    private int staminaPointsPreserving;

    public ParryingWeapon(int diceCnt, int diceType, int staticDamage, String name, int staminaPointsPreserving) {
        super(diceCnt, diceType, staticDamage, name);
        this.staminaPointsPreserving = staminaPointsPreserving;
    }

    public int getStaminaPointsPreserving() {
        return staminaPointsPreserving;
    }
}
