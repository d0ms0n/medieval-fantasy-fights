package org.d0ms0n.mfa.game;

import javax.inject.Singleton;
import java.security.SecureRandom;

@Singleton
public class Dice {
    SecureRandom secureRandom = new SecureRandom();

    public int roll(int dice) {
        return secureRandom.nextInt(dice) + 1;
    }

    public int r20() {
        return roll(20);
    }

    public int r6() {
        return roll(6);
    }

    public int r100() {
        return roll(100);
    }
}
