package org.d0ms0n.mfa.model;

public class ParryingWeapon extends Weapon {

    private int staminaPointsPreserving;

    public ParryingWeapon() {
    }

    public ParryingWeapon(String name, String description, int staminaPointsPreserving, float weight, float value, RawWeapon rawWeapon, boolean equipped) {
        super(name, description, weight, value, rawWeapon, equipped);
        this.staminaPointsPreserving = staminaPointsPreserving;
    }

    public int getStaminaPointsPreserving() {
        return staminaPointsPreserving;
    }
}
