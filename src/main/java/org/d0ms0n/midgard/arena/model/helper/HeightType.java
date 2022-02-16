package org.d0ms0n.midgard.arena.model.helper;

public enum HeightType {
    SHORT("klein"),
    MEDIUM("mittelgroß"),
    LARGE("groß");

    HeightType(String heightType) {
    }

    public static HeightType getHeightTypeFromSeize(Race race, int height) {
        if (race.equals(Race.HUMAN) || race.equals(Race.ELF)) {
            if (height > 180) {
                return HeightType.LARGE;
            }
            if (height > 165) {
                return HeightType.MEDIUM;
            }
        }
        return HeightType.SHORT;
    }
}
