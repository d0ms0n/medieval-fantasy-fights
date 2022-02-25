package org.d0ms0n.midgard.arena.model.helper;

//@JsonSerialize(using = SkillTypeSerializer.class)
public enum SkillType {
    COMBAT("Kampf"),
    WEAPON("Waffe"),
    MOVEMENT("Bewegung"),
    KNOWLEDGE("Wissen"),
    SOCIAL("Sozial"),
    BODY_CONTROL("Körperbeherrschung"),
    DEXTERITY("Fingerfertigkeit"),
    DISCOVERY("Entdeckung"),
    SENSE("Sinn");

    final String name;

    SkillType(String name) {
        this.name = name;
    }

}
