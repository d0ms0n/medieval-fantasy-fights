package org.d0ms0n.mfa.arena.model;

import org.d0ms0n.mfa.exception.ContainerFullException;

import java.util.ArrayList;
import java.util.List;

public class Container extends Item {
    boolean countOwnWeight;
    int volumeInLiter;
    int loadCapacity;
    int loadCapacityMax;
    List<Item> content;

    public Container(String name, String description, int weight, int value) {
        super(name, description, weight, value);
    }

    public int getLoadCapacityMax() {
        return loadCapacityMax;
    }

    public void setLoadCapacityMax(int loadCapacityMax) {
        this.loadCapacityMax = loadCapacityMax;
    }

    public boolean isCountOwnWeight() {
        return countOwnWeight;
    }

    public void setCountOwnWeight(boolean countOwnWeight) {
        this.countOwnWeight = countOwnWeight;
    }

    public int getVolumeInLiter() {
        return volumeInLiter;
    }

    public void setVolumeInLiter(int volumeInLiter) {
        this.volumeInLiter = volumeInLiter;
    }

    public double getLoadCapacity() {
        return content.stream().mapToDouble(Item::getWeight).sum();
    }

    public void setLoadCapacity(int loadCapacity) {
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
