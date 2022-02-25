package org.d0ms0n.midgard.arena.model.helper;

import org.d0ms0n.midgard.arena.model.Character;
import org.d0ms0n.midgard.arena.model.*;

import javax.enterprise.context.RequestScoped;
import java.lang.reflect.Array;
import java.util.List;

@RequestScoped
public class CharacterBuilder {

    String name;
    int level;
    int experience;
    int experiencePoints;
    Array specialization;
    Gender gender;
    Race race;
    Profession profession;
    int heightInCm;
    int weightInKg;
    HeightType heightType;
    WidthType widthType;
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
    int defense;
    int resistanceMind;
    int resistanceBody;
    int magicBonus;
    long squadId;
    String handed;
    Characteristics characteristics;
    Attributes attributes;
    Bennies bennies;
    List<Skill> skills;
    List<Spell> spells;
    List<String> specialisations;
    List<Item> equipment;
    List<Container> container;
    List<Vehicle> meansOfTransportation;

    public CharacterBuilder() {

    }

    public CharacterBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public CharacterBuilder withExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public CharacterBuilder withExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
        return this;
    }

    public CharacterBuilder withSpecialization(Array specialization) {
        this.specialization = specialization;
        return this;
    }

    public CharacterBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public CharacterBuilder withRace(Race race) {
        this.race = race;
        return this;
    }

    public CharacterBuilder withProfession(Profession profession) {
        this.profession = profession;
        return this;
    }

    public CharacterBuilder withHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
        return this;
    }

    public CharacterBuilder withWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
        return this;
    }

    public CharacterBuilder withHeightType(HeightType heightType) {
        this.heightType = heightType;
        return this;
    }

    public CharacterBuilder withWidthType(WidthType widthType) {
        this.widthType = widthType;
        return this;
    }

    public CharacterBuilder withStanding(String standing) {
        this.standing = standing;
        return this;
    }

    public CharacterBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public CharacterBuilder withFaith(String faith) {
        this.faith = faith;
        return this;
    }

    public CharacterBuilder withLifePointsMax(int lifePointsMax) {
        this.lifePointsMax = lifePointsMax;
        return this;
    }

    public CharacterBuilder withStaminaPointsMax(int staminaPointsMax) {
        this.staminaPointsMax = staminaPointsMax;
        return this;
    }

    public CharacterBuilder withLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
        return this;
    }

    public CharacterBuilder withStaminaPoints(int staminaPoints) {
        this.staminaPoints = staminaPoints;
        return this;
    }

    public CharacterBuilder withMovement(int movement) {
        this.movement = movement;
        return this;
    }

    public CharacterBuilder withAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
        return this;
    }

    public CharacterBuilder withDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
        return this;
    }

    public CharacterBuilder withDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public CharacterBuilder withResistanceMind(int resistanceMind) {
        this.resistanceMind = resistanceMind;
        return this;
    }

    public CharacterBuilder withResistanceBody(int resistanceBody) {
        this.resistanceBody = resistanceBody;
        return this;
    }

    public CharacterBuilder withMagicBonus(int magicBonus) {
        this.magicBonus = magicBonus;
        return this;
    }

    public CharacterBuilder withSquadId(long squadId) {
        this.squadId = squadId;
        return this;
    }

    public CharacterBuilder withHanded(String handed) {
        this.handed = handed;
        return this;
    }

    public CharacterBuilder withCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    public CharacterBuilder withAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public CharacterBuilder withBennies(Bennies bennies) {
        this.bennies = bennies;
        return this;
    }

    public CharacterBuilder withSkills(List<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public CharacterBuilder withSpells(List<Spell> spells) {
        this.spells = spells;
        return this;
    }

    public CharacterBuilder withSpecialisations(List<String> specialisations) {
        this.specialisations = specialisations;
        return this;
    }

    public CharacterBuilder withEquipment(List<Item> equipment) {
        this.equipment = equipment;
        return this;
    }

    public CharacterBuilder withContainer(List<Container> container) {
        this.container = container;
        return this;
    }

    public CharacterBuilder withMeansOfTransportation(List<Vehicle> meansOfTransportation) {
        this.meansOfTransportation = meansOfTransportation;
        return this;
    }

    public Character build() {
        return new Character(this);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public Array getSpecialization() {
        return specialization;
    }

    public Gender getGender() {
        return gender;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public HeightType getHeightType() {
        return heightType;
    }

    public WidthType getWidthType() {
        return widthType;
    }

    public String getStanding() {
        return standing;
    }

    public int getAge() {
        return age;
    }

    public String getFaith() {
        return faith;
    }

    public int getLifePointsMax() {
        return lifePointsMax;
    }

    public int getStaminaPointsMax() {
        return staminaPointsMax;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getStaminaPoints() {
        return staminaPoints;
    }

    public int getMovement() {
        return movement;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public int getDefense() {
        return defense;
    }

    public int getResistanceMind() {
        return resistanceMind;
    }

    public int getResistanceBody() {
        return resistanceBody;
    }

    public int getMagicBonus() {
        return magicBonus;
    }

    public long getSquadId() {
        return squadId;
    }

    public String getHanded() {
        return handed;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Bennies getBennies() {
        return bennies;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public List<String> getSpecialisations() {
        return specialisations;
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public List<Container> getContainer() {
        return container;
    }

    public List<Vehicle> getMeansOfTransportation() {
        return meansOfTransportation;
    }
}
