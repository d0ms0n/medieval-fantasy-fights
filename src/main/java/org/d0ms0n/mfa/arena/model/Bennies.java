package org.d0ms0n.mfa.arena.model;

public class Bennies {
    int fatePoints;
    int divinePoints;
    int luckPoints;

    public Bennies(int fatePoints, int divinePoints, int luckPoints) {
        this.fatePoints = fatePoints;
        this.divinePoints = divinePoints;
        this.luckPoints = luckPoints;
    }

    public int getFatePoints() {
        return fatePoints;
    }

    public void setFatePoints(int fatePoints) {
        this.fatePoints = fatePoints;
    }

    public int getDivinePoints() {
        return divinePoints;
    }

    public void setDivinePoints(int divinePoints) {
        this.divinePoints = divinePoints;
    }

    public int getLuckPoints() {
        return luckPoints;
    }

    public void setLuckPoints(int luckPoints) {
        this.luckPoints = luckPoints;
    }
}
