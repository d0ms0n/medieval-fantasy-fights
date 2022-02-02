package org.d0ms0n.midgard.arena.game;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

public class Squad {
    private String name;
    private long squadId;
    private List<Fighter> members;
    private SecureRandom secureRandom = new SecureRandom();

    public Squad(String name, List<Fighter> members) {
        this.name = name;
        this.members = members;
        this.squadId = secureRandom.nextLong();
    }

    public long getSquadId() {
        return squadId;
    }

    public List<Fighter> getMembers() {
        return members;
    }

    public void setMembers(List<Fighter> members) {
        this.members = members;
    }
}
