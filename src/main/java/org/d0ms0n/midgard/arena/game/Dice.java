package org.d0ms0n.midgard.arena.game;

import java.security.SecureRandom;

public class Dice {

    public static int roll(int dice) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(dice)+1;
    }

    public static int r20() {
        return roll(20);
    }

    public static int r6() {
        return roll(6);
    }

    public static int r100() {
        return roll(100);
    }
}
