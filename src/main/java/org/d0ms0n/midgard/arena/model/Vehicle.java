package org.d0ms0n.midgard.arena.model;

public class Vehicle extends Container {
    int kmPerHour;
    int meterPerRound;

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
