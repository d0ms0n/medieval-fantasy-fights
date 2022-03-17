package org.d0ms0n.mfa.model.helper;

import java.util.Arrays;

public enum Race {
    GNOME("Gnome", "<= 12", " >= 16", ">= 16", ">= 10", "<= 20", "<= 20", -3, 4, 8),
    HALFLING("Halfling", "<= 16", ">= 12", ">= 18", ">= 8", "<= 20", "<= 20", -2, 4, 8),
    DWARF("Dwarf", ">= 12", "<= 20", "<= 16", ">= 12", "<= 20", "<= 20", 1, 3, 12),
    ELF("Elf", "<= 18", "<= 20", ">= 16", ">= 10", ">= 10", ">= 10", 0, 2, 16),
    HUMAN("Human", "<= 20", "<= 20", "<= 20", "<= 20", "<= 20", "<= 20", 0, 0, 16);

    private final String name;
    private final String strength;
    private final String dexterity;
    private final String agility;
    private final String constitution;
    private final String intelligence;
    private final String magicTalent;
    private final int lifePoints;
    private final int resistance;
    private final int movementBase;

    Race(String name, String strength, String dexterity, String agility, String constitution, String intelligence, String magicTalent, int lifePoints, int resistance, int movementBase) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.magicTalent = magicTalent;
        this.lifePoints = lifePoints;
        this.resistance = resistance;
        this.movementBase = movementBase;
    }

    public static Race getRaceFromName(String name) {
        return Arrays.stream(Race.values())
                .filter(race -> race.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(HUMAN);
    }

    public String getName() {
        return name;
    }

    public String getStrength() {
        return strength;
    }

    public String getDexterity() {
        return dexterity;
    }

    public String getAgility() {
        return agility;
    }

    public String getConstitution() {
        return constitution;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getMagicTalent() {
        return magicTalent;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getResistance() {
        return resistance;
    }

    public int getMovementBase() {
        return movementBase;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
