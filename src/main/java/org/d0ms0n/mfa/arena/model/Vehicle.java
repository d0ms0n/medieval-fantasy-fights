package org.d0ms0n.mfa.arena.model;

public class Vehicle extends Container {
    int kmPerHour;
    int meterPerRound;

    public Vehicle(String name, String description, int weight, int value) {
        super(name, description, weight, value);
    }

    public int getKmPerHour() {
        return kmPerHour;
    }

    public void setKmPerHour(int kmPerHour) {
        this.kmPerHour = kmPerHour;
    }

    public int getMeterPerRound() {
        return meterPerRound;
    }

    public void setMeterPerRound(int meterPerRound) {
        this.meterPerRound = meterPerRound;
    }

    @Override
    public int getCounter() {
        return 0;
    }

    @Override
    public int getCounterMax() {
        return 0;
    }

    @Override
    public int getVolumeInLiter() {
        return 0;
    }

    @Override
    public boolean isCountOwnWeight() {
        return false;
    }
}
