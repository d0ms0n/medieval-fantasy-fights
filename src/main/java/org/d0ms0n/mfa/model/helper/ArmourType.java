package org.d0ms0n.mfa.model.helper;

public enum ArmourType {
    NONE("No armour", 0),
    TEXTILE("Textile armour", 1),
    LEATHER("Leather armour", 2),
    CHAIN("Chain armour", 3),
    PLATE("Plate armour", 4),
    FULL("Full armour", 5);

    final String name;
    final int armourClassBonus;

    ArmourType(String name, int armourClassBonus) {
        this.name = name;
        this.armourClassBonus = armourClassBonus;
    }

    public String getName() {
        return name;
    }

    public int getArmourClassBonus() {
        return armourClassBonus;
    }
}
