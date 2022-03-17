package org.d0ms0n.mfa.model;

import org.d0ms0n.mfa.exception.ContainerFullException;

import java.util.ArrayList;
import java.util.List;

public class Container extends Item {
    boolean countOwnWeight;
    float volumeInLiter;
    float loadCapacity;
    float loadCapacityMax;
    List<Item> content;

    public Container(String name, String description, float weight, float value) {
        super(name, description, weight, value);
    }

    public float getLoadCapacityMax() {
        return loadCapacityMax;
    }

    public void setLoadCapacityMax(float loadCapacityMax) {
        this.loadCapacityMax = loadCapacityMax;
    }

    public boolean isCountOwnWeight() {
        return countOwnWeight;
    }

    public void setCountOwnWeight(boolean countOwnWeight) {
        this.countOwnWeight = countOwnWeight;
    }

    public float getVolumeInLiter() {
        return volumeInLiter;
    }

    public void setVolumeInLiter(float volumeInLiter) {
        this.volumeInLiter = volumeInLiter;
    }

    public float getLoadCapacity() {
        return (float) content.stream().mapToDouble(Item::getWeight).sum();
    }

    public void setLoadCapacity(float loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public List<Item> getContent() {
        return content != null ? content : new ArrayList<>();
    }

    public void setContent(List<Item> content) {
        this.content = content;
    }

    public void add(Item item) throws ContainerFullException {
        if(getLoadCapacity() + item.getWeight() > getLoadCapacityMax()){
            throw new ContainerFullException();
        }
        this.content.add(item);
    }
}
