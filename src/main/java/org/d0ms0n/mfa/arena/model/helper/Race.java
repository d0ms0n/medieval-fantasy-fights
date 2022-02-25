package org.d0ms0n.mfa.arena.model.helper;

import java.util.Arrays;

public enum Race {
    GNOME("Gnom", "<= 60", " >= 81", ">= 81", ">= 51", "<= 100", "<= 100", "<= 80", -3, 4),
    HALFLING("Halbling", "<= 80", ">= 61", ">= 91", ">= 41", "<= 100", "<= 100", "<= 100", -2, 4),
    DWARF("Zwerg", ">= 61", "<= 100", "<= 80", ">= 61", "<= 100", "<= 100", "<= 80", 1, 3),
    ELF("Elf", "<= 90", "<= 100", ">= 81", ">= 51", ">= 51", ">= 51", ">= 81", 0, 2),
    HUMAN("Mensch", "<= 100", "<= 100", "<= 100", "<= 100", "<= 100", "<= 100", "<= 100", 0, 0);

    private final String name;
    private final String strength;
    private final String dexterity;
    private final String agility;
    private final String constitution;
    private final String intelligence;
    private final String magicTalent;
    private final String look;
    private final int lifePoints;
    private final int resistence;

    Race(String name, String strength, String dexterity, String agility, String constitution, String intelligence, String magicTalent, String look, int lifePoints, int resistence) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.magicTalent = magicTalent;
        this.look = look;
        this.lifePoints = lifePoints;
        this.resistence = resistence;
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

    public String getLook() {
        return look;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getResistence() {
        return resistence;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
