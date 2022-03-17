package org.d0ms0n.mfa.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.d0ms0n.mfa.model.helper.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MongoEntity(collection = "characters")
public class Character implements Comparable<Character> {
    @BsonId
    ObjectId id;
    String userId;
    String name;
    int level;
    int experience;
    int experiencePoints;
    float coins;
    Array specialization;
    String gender;
    Race race;
    Profession profession;
    int lifePointsMax;
    int staminaPointsMax;
    int lifePoints;
    int staminaPoints;
    long squadId;
    String handed;
    Attributes attributes;
    List<Skill> skills;
    List<String> specialisations;
    List<Item> equipment;
    List<Container> container;

    public Character() {
    }

    public Character(String userId, String name, Profession profession, int level, Attributes attributes) {
        this.userId = userId;
        this.name = name;
        this.profession = profession;
        this.level = level;
        this.attributes = attributes;
        this.race = Race.HUMAN;
        this.setLifePoints(getLifePointsMax());
        this.setStaminaPoints(getStaminaPointsMax());
        this.coins = 80;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public float getCoins() {
        return coins;
    }

    public void setCoins(float coins) {
        this.coins = coins;
    }

    public int getResistanceMind() {
        return calcDefenseAndResistanceFromLevel();
    }

    public int getFullResistanceMind() {
        if (getRace() != Race.HUMAN) {
            if (this.profession.getAdventurerType() != AdventurerType.SORCERER) {
                return getResistanceMind();
            }
            return getResistanceMind() + getRace().getResistance() + 2;
        }

        int bonus = getBonus(this.getAttributes().getIntelligence());
        return getResistanceMind() + bonus + (this.profession.getAdventurerType() == AdventurerType.SORCERER ? 2 : 1);
    }

    public int getFullResistanceBody() {
        if (getRace() != Race.HUMAN) {
            return getResistanceBody() + getRace().getResistance() + (this.profession.getAdventurerType() == AdventurerType.SORCERER ? 2 : 1);
        }
        int bonus = getBonus(this.getAttributes().getConstitution());
        return getResistanceBody() + bonus + (this.profession.getAdventurerType() == AdventurerType.SORCERER ? 2 : 1);
    }

    public int getResistanceBody() {
        return calcDefenseAndResistanceFromLevel();
    }

    public int getStaminaBonus() {
        return (int) (Math.floor(attributes.getConstitution() / 2.0) + Math.floor(attributes.getStrength() / 4.0));
    }

    public int getDamageBonus() {
        return (int) (Math.floor((double) this.attributes.getStrength() / 4.0) + Math.floor((double) this.attributes.getDexterity() / 6.0) - 3);
    }

    public int getAttackBonus() {
        return getBonus(this.getAttributes().getDexterity());
    }

    public int getDefenseBonus() {
        int bonus = getBonus(this.getAttributes().getAgility());
        int lossOfDefenseBonusFromArmour = getEquipment().stream()
                .filter(item -> item instanceof Armour && ((Armour) item).isPutOn())
                .mapToInt(item -> ((Armour) item).getLossOfDefenseBonus())
                .sum();

        return bonus - lossOfDefenseBonusFromArmour;
    }

    public int getMagicBonus() {
        return getBonus(this.getAttributes().getMagicTalent());
    }

    private int getBonus(int attribute) {
        if (attribute > 16) {
            return attribute == 20 ? 2 : 1;
        }
        if (attribute <= 4) {
            return attribute == 1 ? -2 : -1;
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
                .filter(skill -> skill.getName().equals(weapon.getRawWeapon().getType().getName()))
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

    private Weapon getFist() {
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

    public int getLifePointsMax() {
        return (int) (9 + Math.floor(attributes.getConstitution() / 2.0));
    }

    public void setLifePointsMax(int lifePointsMax) {
        this.lifePointsMax = lifePointsMax;
    }

    public int getStaminaPointsMax() {
        return 3 + this.getStaminaBonus();
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
        return getRace().getMovementBase() + (int) (Math.floor(getAttributes().getAgility() / 2.0));
    }

    public String getHanded() {
        return handed;
    }

    public void setHanded(String handed) {
        this.handed = handed;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
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

    @Override
    public int compareTo(Character character) {
        return character.getAttributes().getAgility() - getAttributes().getAgility();
    }
}
