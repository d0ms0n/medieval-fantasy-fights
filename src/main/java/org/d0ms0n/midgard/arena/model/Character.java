package org.d0ms0n.midgard.arena.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.d0ms0n.midgard.arena.model.helper.HeightType;
import org.d0ms0n.midgard.arena.model.helper.SkillType;
import org.d0ms0n.midgard.arena.model.helper.WeaponType;
import org.d0ms0n.midgard.arena.model.helper.WidthType;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MongoEntity(collection = "character")
public class Character implements Comparable<Character> {
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
    String[] specialisations;
    List<Item> equipment;
    List<Container> container;
    List<Vehicle> meansOfTransportation;


    public Character(String name, long squadId, int lifePoints, int lifePointsMax, int staminaPoints, int staminaPointsMax, int defense, Attributes attributes, String[] specialisations, List<Skill> skills, List<Item> equipment) {
        this.name = name;
        this.squadId = squadId;
        this.lifePointsMax = lifePointsMax;
        this.staminaPointsMax = staminaPointsMax;
        this.lifePoints = lifePoints;
        this.staminaPoints = staminaPoints;
        this.defense = defense;
        this.attributes = attributes;
        this.equipment = equipment;
        this.skills = skills;
        this.specialisations = specialisations;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getResistanceMind() {
        return resistanceMind;
    }

    public void setResistanceMind(int resistanceMind) {
        this.resistanceMind = resistanceMind;
    }

    public int getResistanceBody() {
        return resistanceBody;
    }

    public void setResistanceBody(int resistanceBody) {
        this.resistanceBody = resistanceBody;
    }

    public int getDamageBonus() {
        return (int) (Math.floor((double) this.attributes.getStrength() / 20.0) + Math.floor((double) this.attributes.getDexterity() / 30.0) - 3);
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
        int bonus = 0;
        if (agility > 80) {
            bonus = agility > 95 ? 2 : 1;
        }
        if (agility < 21) {
            bonus = agility < 6 ? -2 : -1;
        }

        int lossOfDefenseBonusFromArmour = getEquipment().stream()
                .filter(item -> item instanceof Armour && ((Armour) item).isPutOn())
                .mapToInt(item -> ((Armour) item).getLossOfDefenseBonus())
                .sum();


        return bonus - lossOfDefenseBonusFromArmour;
    }

    public int getMagicBonus() {
        int magicTalent = this.getAttributes().getMagicTalent();
        if (magicTalent > 80) {
            return magicTalent > 95 ? 2 : 1;
        }
        if (magicTalent < 21) {
            return magicTalent < 6 ? -2 : -1;
        }
        return 0;
    }

    public int getArmourClass() {
        return getEquipment().stream()
                .filter(item -> item instanceof Armour && ((Armour) item).isPutOn())
                .mapToInt(item -> ((Armour) item).getArmourClassBonus())
                .sum();
    }

    private int getDefense() {
        return defense;
    }

    public int getFullDefense() {
        return getDefense() + getDefenseBonus();
    }

    public ParryingWeapon getParryingWeapon() {
        return (ParryingWeapon) getEquipment().stream()
                .filter(item -> item instanceof ParryingWeapon && ((ParryingWeapon) item).isEquipped())
                .findFirst().orElse(null);
    }

    public int getAttackValue(Weapon weapon) {
        int attackValue = getAttackBonus();

        if (isSpecialisation(weapon)) {
            attackValue += 2;
        }

        Optional<Skill> weaponSkill = getWeaponSkills().stream()
                .filter(skill -> skill.getName().equals(weapon.getWeaponType().getName()))
                .findFirst();

        if (weaponSkill.isPresent()) {
            attackValue += weaponSkill.get().getValue();
        } else {
            attackValue += 4;
        }

        return attackValue;
    }

    private boolean isSpecialisation(Weapon weapon) {
        return Arrays.stream(this.specialisations).anyMatch(s -> s.equals(weapon.getName()));
    }

    public Weapon getAttackWeapon() {
        return (Weapon) getEquipment().stream()
                .filter(item -> item instanceof Weapon && ((Weapon) item).isEquipped())
                .findFirst().orElse(getFist());
    }

    public long getSquadId() {
        return squadId;
    }

    public Weapon getFist() {
        return new Weapon(1, 6, -4, "Faust", WeaponType.UNSKILLED, true);
    }

    public void setSquadId(long squadId) {
        this.squadId = squadId;
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

    public HeightType getHeightType() {
        return heightType;
    }

    public void setHeightType(HeightType heightType) {
        this.heightType = heightType;
    }

    public WidthType getWidthType() {
        return widthType;
    }

    public void setWidthType(WidthType widthType) {
        this.widthType = widthType;
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

    public List<Skill> getWeaponSkills() {
        return getSkills().stream().filter(skill -> skill.getType().equals(SkillType.WEAPON)).collect(Collectors.toList());
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

    @Override
    public int compareTo(Character character) {
        return character.getAttributes().getAgility() - getAttributes().getAgility();
    }
}
