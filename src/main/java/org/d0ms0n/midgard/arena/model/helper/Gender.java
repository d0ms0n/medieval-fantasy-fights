package org.d0ms0n.midgard.arena.model.helper;

public enum Gender {
    MALE("männlich"),
    FEMALE("weiblich"),
    DIVERSE("divers");

    final String name;

    Gender(String name) {
        this.name = name;
    }
}
