package org.d0ms0n.midgard.arena.model;

import java.security.SecureRandom;
import java.util.List;

public class Squad {
    private String name;
    private long squadId;
    private List<Character> members;
    private SecureRandom secureRandom = new SecureRandom();

    public Squad(String name, List<Character> members) {
        this.name = name;
        this.members = members;
        this.squadId = secureRandom.nextLong();
        members.stream().forEach(character -> character.setSquadId(squadId));
    }

    public long getSquadId() {
        return squadId;
    }

    public List<Character> getMembers() {
        return members;
    }

    public void setMembers(List<Character> members) {
        this.members = members;
    }
}
