package org.d0ms0n.mfa.arena.model;

public class Characteristics {
    String hairColor;
    String eyeColor;
    String other;

    public Characteristics(String hairColor, String eyeColor, String other) {
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.other = other;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
