package org.d0ms0n.mfa.arena.model.helper;

import java.util.Arrays;

public enum Profession {
    ASSASSIN("Assassine", "As", "GS, Gw", AdventurerType.FIGHTER),
    BARBARIAN("Barbar", "Bb", "St, Gw", AdventurerType.FIGHTER),
    FORTUNE_KNIGHT("Glücksritter", "Gl", "Gs, pA", AdventurerType.FIGHTER),
    TRADER("Händler", "Hä", "Inn, Gs/pA", AdventurerType.FIGHTER),
    WARRIOR("Krieger", "Kr", "St, Gw", AdventurerType.FIGHTER),
    THIEF("Spitzbube", "Sp", "Gs, Gw/In", AdventurerType.FIGHTER),
    RANGER("Waldläufer", "Wa", "Gw, In", AdventurerType.FIGHTER),
    PALADIN("Ordenskrieger", "Or", "St, Zt", AdventurerType.MAGICAL_FIGHTER);

    String name;
    String shortName;
    String leadingAttributes;
    AdventurerType adventurerType;

    Profession(String name, String shortName, String leadingAttributes, AdventurerType adventurerType) {
        this.name = name;
        this.shortName = shortName;
        this.leadingAttributes = leadingAttributes;
        this.adventurerType = adventurerType;
    }

    public static Profession getProfessionFromName(String name) {
        return Arrays.stream(Profession.values())
                .filter(race -> race.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(WARRIOR);
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLeadingAttributes() {
        return leadingAttributes;
    }

    public AdventurerType getAdventurerType() {
        return adventurerType;
    }
}
