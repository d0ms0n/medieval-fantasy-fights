package org.d0ms0n.midgard.arena.model;

import org.d0ms0n.midgard.arena.model.helper.WeaponType;

public class ParryingWeapon extends Weapon {

    private final int staminaPointsPreserving;

    public ParryingWeapon(int diceCnt, int diceType, int staticDamage, String name, int staminaPointsPreserving, WeaponType weaponType, boolean equipped) {
        super(diceCnt, diceType, staticDamage, name, weaponType, equipped);
        this.staminaPointsPreserving = staminaPointsPreserving;
    }

    public int getStaminaPointsPreserving() {
        return staminaPointsPreserving;
    }
}
