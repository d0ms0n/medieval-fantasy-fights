package org.d0ms0n.mfa.model;

public class Attributes {
    int strength;
    int dexterity;
    int agility;
    int constitution;
    int intelligence;
    int magicTalent;
    int charisma;

    public Attributes() {
    }

    public Attributes(int strength, int dexterity, int agility, int constitution, int intelligence, int magicTalent, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.magicTalent = magicTalent;
        this.charisma = charisma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMagicTalent() {
        return magicTalent;
    }

    public void setMagicTalent(int magicTalent) {
        this.magicTalent = magicTalent;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

}
