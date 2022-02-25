package org.d0ms0n.midgard.arena.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.d0ms0n.midgard.arena.model.helper.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MongoEntity(collection = "character")
public class Character implements Comparable<Character> {
    String name;
    int level;
    int experience;
    int experiencePoints;
    Array specialization;
    String gender;
    Race race;
    Profession profession;
    int heightInCm;
    int weightInKg;
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


    public Character(String name, long squadId, int lifePoints, int lifePointsMax, int staminaPoints, int staminaPointsMax, int level, Attributes attributes, List<String> specialisations, List<Skill> skills, List<Item> equipment) {
        this.name = name;
        this.squadId = squadId;
        this.lifePointsMax = lifePointsMax;
        this.staminaPointsMax = staminaPointsMax;
        this.lifePoints = lifePoints;
        this.staminaPoints = staminaPoints;
        this.level = level;
        this.attributes = attributes;
        this.equipment = equipment;
        this.skills = skills;
        this.specialisations = specialisations;
    }

    public Character(CharacterBuilder characterBuilder) {
        this.name = characterBuilder.getName();
        this.race = characterBuilder.getRace();
        this.profession = characterBuilder.getProfession();
        this.squadId = characterBuilder.getSquadId();
        this.lifePointsMax = characterBuilder.getLifePointsMax();
        this.staminaPointsMax = characterBuilder.getStaminaPointsMax();
        this.lifePoints = characterBuilder.getLifePoints();
        this.staminaPoints = characterBuilder.getStaminaPoints();
        this.attributes = characterBuilder.getAttributes();
        this.specialisations = characterBuilder.getSpecialisations();
        this.level = characterBuilder.getLevel();
        this.equipment = characterBuilder.getEquipment();
        this.skills = characterBuilder.getSkills();
        this.specialisations = characterBuilder.getSpecialisations();
        this.movement = characterBuilder.getMovement();
        this.age = characterBuilder.getAge();
        this.characteristics = characterBuilder.getCharacteristics();
        this.bennies = characterBuilder.getBennies();
        this.experience = characterBuilder.getExperience();
        this.faith = characterBuilder.getFaith();
        this.heightInCm = characterBuilder.getHeightInCm();
        this.weightInKg = characterBuilder.getWeightInKg();
    }

    public int getResistanceMind() {
        return calcDefenseAndResistanceFromLevel();
    }

    public int getFullResistanceMind() {
        return getResistanceMind() + 0;
    }

    public int getFullResistanceBody() {
        return getResistanceBody() + 0;
    }

    public int getResistanceBody() {
        return calcDefenseAndResistanceFromLevel();
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

    public int getDefense() {
        return calcDefenseAndResistanceFromLevel();
    }

    private int calcDefenseAndResistanceFromLevel() {
        if (level == 1) {
            return 11;
        }
        if (level == 2) {
            return 12;
        }
        if (level >= 3 && level < 5) {
            return 13;
        }
        if (level >= 5 && level < 10) {
            return 14;
        }
        if (level >= 10 && level < 15) {
            return 15;
        }
        if (level >= 15 && level < 20) {
            return 15;
        }
        if (level >= 20 && level < 25) {
            return 15;
        }
        if (level >= 25 && level < 30) {
            return 15;
        }
        return 11;
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
                .filter(skill -> skill.getName().equals(weapon.getRawWeapon().getWeaponType().getName()))
                .findFirst();

        if (weaponSkill.isPresent()) {
            attackValue += weaponSkill.get().getValue();
        } else {
            attackValue += 4;
        }

        return attackValue;
    }

    private boolean isSpecialisation(Weapon weapon) {
        return this.specialisations.stream().anyMatch(s -> s.equals(weapon.getName()));
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
        return new Weapon("Faust", "Faust", 0.0F, 0.0F, new RawWeapon(1, 6, -4, "Faust", WeaponType.UNSKILLED), true);
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
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
        return HeightType.getHeightTypeFromSeize(this.race, this.heightInCm);
    }

    public WidthType getWidthType() {
        return WidthType.getWidthTypeFromSizeAndWeight(this.race, this.heightInCm, this.weightInKg);
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
        if (attributes == null) {
            attributes = new Attributes(0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
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
        if (skills == null) {
            skills = new ArrayList<>();
        }
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
        if (equipment == null) {
            equipment = new ArrayList<>();
        }
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
    }

    public List<Container> getContainer() {
        if (container == null) {
            container = new ArrayList<>();
        }
        return container;
    }

    public void setContainer(List<Container> container) {
        this.container = container;
    }

    public List<Vehicle> getMeansOfTransportation() {
        if (meansOfTransportation == null) {
            meansOfTransportation = new ArrayList<>();
        }
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
