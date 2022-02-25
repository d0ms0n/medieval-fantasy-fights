package org.d0ms0n.midgard.arena.model;

import org.bson.types.ObjectId;

public class Item {
    ObjectId id;
    String name;
    boolean magical;
    String description;
    float weight;
    float value;
    int counterMax;
    int counter;

    public Item(String name, String description, float weight, float value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public Item() {
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

    public boolean isMagical() {
        return magical;
    }

    public void setMagical(boolean magical) {
        this.magical = magical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getCounterMax() {
        return counterMax;
    }

    public void setCounterMax(int counterMax) {
        this.counterMax = counterMax;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
