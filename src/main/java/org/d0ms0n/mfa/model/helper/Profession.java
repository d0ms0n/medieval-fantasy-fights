package org.d0ms0n.mfa.model.helper;

import java.util.Arrays;

public enum Profession {
    BARBARIAN("Barbarian", "Strength, Agility", AdventurerType.FIGHTER, 3),
    ADVENTURER("Adventurer", "Dexterity, Charisma", AdventurerType.FIGHTER, 3),
    WARRIOR("Warrior", "Strength, Agility", AdventurerType.FIGHTER, 4),
    THIEF("Thief", "Dexterity, Agility", AdventurerType.FIGHTER, 2),
    RANGER("Ranger", "Agility, Intelligence", AdventurerType.FIGHTER, 3);

    String name;
    String leadingAttributes;
    AdventurerType adventurerType;
    int numberOfWeaponsOnStart;

    Profession(String name, String leadingAttributes, AdventurerType adventurerType, int numberOfWeaponsOnStart) {
        this.name = name;
        this.leadingAttributes = leadingAttributes;
        this.adventurerType = adventurerType;
        this.numberOfWeaponsOnStart = numberOfWeaponsOnStart;
    }

    public static Profession getProfessionFromName(String name) {
        return Arrays.stream(Profession.values())
                .filter(race -> race.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public String getLeadingAttributes() {
        return leadingAttributes;
    }

    public int getNumberOfWeaponsOnStart() {
        return numberOfWeaponsOnStart;
    }

    public AdventurerType getAdventurerType() {
        return adventurerType;
    }
}
