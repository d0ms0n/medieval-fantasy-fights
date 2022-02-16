package org.d0ms0n.midgard.arena.model.helper;

public enum SkillType {
    COMBAT("Kampf"),
    WEAPON("Waffe"),
    MOVEMENT("Bewegung"),
    KNOWLEDGE("Wissen"),
    SOCIAL("Sozial"),
    BODY_CONTROL("Körperbeherrschung"),
    DEXTERITY("Fingerfertigkeit"),
    DISCOVERY("Entdeckung");

    final String name;

    SkillType(String name) {
        this.name = name;
    }

}
