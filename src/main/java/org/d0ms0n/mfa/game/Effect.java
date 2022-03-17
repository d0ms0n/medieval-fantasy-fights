package org.d0ms0n.mfa.game;

public class Effect {
    private int rounds;
    private BattleEffect effect;

    public Effect(int rounds, BattleEffect effect) {
        this.rounds = rounds;
        this.effect = effect;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public BattleEffect getEffect() {
        return effect;
    }

    public void setEffect(BattleEffect effect) {
        this.effect = effect;
    }
}
