package org.d0ms0n.mfa.arena.model.helper;

public enum WidthType {
    SLIM("schlank"),
    MEDIUM("normal"),
    WIDE("breit");

    WidthType(String widthType) {
    }

    public static WidthType getWidthTypeFromSizeAndWeight(Race race, int heightInCm, int weightInKg) {
        int normalWeight;

        if (race.equals(Race.HUMAN) || race.equals(Race.ELF)) {
            normalWeight = heightInCm - 100;
        } else {
            normalWeight = heightInCm - 70;
        }

        double tenth = Math.floor(normalWeight / 10.0);

        if (weightInKg - normalWeight >= tenth) {
            return WidthType.WIDE;
        }
        if (weightInKg - normalWeight < tenth) {
            return WidthType.SLIM;
        }

        return WidthType.MEDIUM;
    }
}
