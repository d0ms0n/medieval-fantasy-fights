package org.d0ms0n.mfa.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;
import org.d0ms0n.mfa.model.helper.WeaponType;

@MongoEntity(collection = "weapon")
public class RawWeapon {
    ObjectId id;
    int diceCount;
    int diceType;
    int staticDamage;
    String name;
    WeaponType type;

    public RawWeapon(int diceCount, int diceType, int staticDamage, String name, WeaponType type) {
        this.diceCount = diceCount;
        this.diceType = diceType;
        this.staticDamage = staticDamage;
        this.name = name;
        this.type = type;
    }

    public RawWeapon() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getDiceType() {
        return diceType;
    }

    public void setDiceType(int diceType) {
        this.diceType = diceType;
    }

    public int getStaticDamage() {
        return staticDamage;
    }

    public void setStaticDamage(int staticDamage) {
        this.staticDamage = staticDamage;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }
}
