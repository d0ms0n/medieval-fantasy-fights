package org.d0ms0n.mfa.arena.model;

public class ParryingWeapon extends Weapon {

    private final int staminaPointsPreserving;

    public ParryingWeapon(String name, String description, int staminaPointsPreserving, double weight, double value, RawWeapon rawWeapon, boolean equipped) {
        super(name, description, weight, value, rawWeapon, equipped);
        this.staminaPointsPreserving = staminaPointsPreserving;
    }

    public int getStaminaPointsPreserving() {
        return staminaPointsPreserving;
    }
}
