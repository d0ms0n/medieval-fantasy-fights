package org.d0ms0n.mfa.model;

import org.d0ms0n.mfa.model.helper.SkillType;

public class Skill extends RawSkill {
    int practicePoints;
    int value;

    public Skill(int unskilled, String name, SkillType type, String source, String leadingAttribute, int practicePoints, int value) {
        super(unskilled, name, type, source, leadingAttribute);
        this.practicePoints = practicePoints;
        this.value = value;
    }

    public Skill(String name, SkillType type, int value) {
        super(0, name, type, null, null);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPracticePoints() {
        return practicePoints;
    }

    public void setPracticePoints(int practicePoints) {
        this.practicePoints = practicePoints;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", unskilled=" + unskilled +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", source='" + source + '\'' +
                ", leadingAttribute='" + leadingAttribute + '\'' +
                '}';
    }
}
