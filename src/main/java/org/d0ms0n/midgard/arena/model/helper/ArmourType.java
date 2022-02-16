package org.d0ms0n.midgard.arena.model.helper;

public enum ArmourType {
    NONE("OR", "ohne Rüstung", 0),
    TEXTILE("TR", "Textilrüstung", 1),
    LEATHER("LR", "Lederrüstung", 2),
    CHAIN("KR", "Kettenrüstung", 3),
    PLATE("PR", "Plattenrüstung", 4),
    FULL("VR", "Vollrüstung", 5),
    KNIGHT("RR", "Ritterrüstung", 6);

    final String name;
    final String fullName;
    final int armourClassBonus;

    ArmourType(String name, String fullName, int armourClassBonus) {
        this.name = name;
        this.fullName = fullName;
        this.armourClassBonus = armourClassBonus;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public int getArmourClassBonus() {
        return armourClassBonus;
    }
}
