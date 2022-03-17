package org.d0ms0n.mfa.model.dto;

import java.util.List;

public class Character1Dto {

    String name;
    String profession;
    List<String> attributeOrder;

    public Character1Dto(String name, String profession, List<String> attributeOrder) {
        this.name = name;
        this.profession = profession;
        this.attributeOrder = attributeOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getAttributeOrder() {
        return attributeOrder;
    }

    public void setAttributeOrder(List<String> attributes) {
        this.attributeOrder = attributes;
    }
}
