package org.d0ms0n.midgard.arena.model;

import io.quarkus.mongodb.panache.MongoEntity;

import java.lang.reflect.Array;
import java.util.List;

@MongoEntity(collection = "character")
public class Character {
    String name;
    int grade;
    int experience;
    int experiencePoints;
    Array specialization;
    String gender;
    String race;
    String type;
    int heightInCm;
    int weightInKg;
    String height;
    String width;
    String standing;
    int age;
    String faith;
    int lifePointsMax;
    int staminaPointsMax;
    int lifePoints;
    int staminaPoints;
    int movement;
    int attackBonus;
    int defenseBonus;
    int magicBonus;
    String handed;
    Characteristics characteristics;
    Attributes attributes;
    Bennies bennies;
    List<Skill> skills;
    List<WeaponSkill> weaponSkills;
    List<Spell> spells;
    List<Item> equipment;
    List<Container> container;
    List<Vehicle> meansOfTransportation;

    public int getDamageBonus() {
        return (int) (Math.ceil((double) this.attributes.getStrength() / 20.0) + Math.ceil((double) this.attributes.getDexterity() / 30.0) - 3);
    }

    public int getAttackBonus() {
        int dexterity = this.getAttributes().getDexterity();
        if (dexterity > 80) {
            return dexterity > 95 ? 2 : 1;
        }
        if (dexterity < 21){
            return dexterity < 6 ? -2 : -1;
        }
        return 0;
    }

    public int getDefenseBonus() {
        int agility = this.getAttributes().getAgility();
        if (agility > 80) {
            return agility > 95 ? 2 : 1;
        }
        if (agility < 21){
            return agility < 6 ? -2 : -1;
        }
        return 0;
    }

    public int getMagicBonus() {
        int magicTalent = this.getAttributes().getMagicTalent();
        if (magicTalent > 80) {
            return magicTalent > 95 ? 2 : 1;
        }
        if (magicTalent < 21){
            return magicTalent < 6 ? -2 : -1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Array getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Array specialization) {
        this.specialization = specialization;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public int getLifePointsMax() {
        return lifePointsMax;
    }

    public void setLifePointsMax(int lifePointsMax) {
        this.lifePointsMax = lifePointsMax;
    }

    public int getStaminaPointsMax() {
        return staminaPointsMax;
    }

    public void setStaminaPointsMax(int staminaPointsMax) {
        this.staminaPointsMax = staminaPointsMax;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getStaminaPoints() {
        return staminaPoints;
    }

    public void setStaminaPoints(int staminaPoints) {
        this.staminaPoints = staminaPoints;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public String getHanded() {
        return handed;
    }

    public void setHanded(String handed) {
        this.handed = handed;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Bennies getBennies() {
        return bennies;
    }

    public void setBennies(Bennies bennies) {
        this.bennies = bennies;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<WeaponSkill> getWeaponSkills() {
        return weaponSkills;
    }

    public void setWeaponSkills(List<WeaponSkill> weaponSkills) {
        this.weaponSkills = weaponSkills;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public List<Container> getContainer() {
        return container;
    }

    public void setContainer(List<Container> container) {
        this.container = container;
    }

    public List<Vehicle> getMeansOfTransportation() {
        return meansOfTransportation;
    }

    public void setMeansOfTransportation(List<Vehicle> meansOfTransportation) {
        this.meansOfTransportation = meansOfTransportation;
    }
}
