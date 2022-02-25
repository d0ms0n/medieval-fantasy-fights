package org.d0ms0n.midgard.arena.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;
import org.d0ms0n.midgard.arena.model.helper.WeaponType;

@MongoEntity(collection = "weapon")
public class RawWeapon {
    ObjectId id;
    int diceCnt;
    int diceType;
    int staticDamage;
    String weaponName;
    WeaponType weaponType;

    public RawWeapon(int diceCnt, int diceType, int staticDamage, String weaponName, WeaponType weaponType) {
        this.diceCnt = diceCnt;
        this.diceType = diceType;
        this.staticDamage = staticDamage;
        this.weaponName = weaponName;
        this.weaponType = weaponType;
    }

    public RawWeapon() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getDiceCnt() {
        return diceCnt;
    }

    public void setDiceCnt(int diceCnt) {
        this.diceCnt = diceCnt;
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

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
}
