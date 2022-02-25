package org.d0ms0n.mfa.arena.model;

import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;
import org.d0ms0n.mfa.arena.model.helper.SkillType;

@MongoEntity(collection = "skill")
public class RawSkill {

    ObjectId id;
    int unskilled;
    String name;
    SkillType type;
    String source;
    String leadingAttribute;

    public RawSkill() {
    }

    public RawSkill(int unskilled, String name, SkillType type, String source, String leadingAttribute) {
        this.unskilled = unskilled;
        this.name = name;
        this.type = type;
        this.source = source;
        this.leadingAttribute = leadingAttribute;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getUnskilled() {
        return unskilled;
    }

    public void setUnskilled(int unskilled) {
        this.unskilled = unskilled;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLeadingAttribute() {
        return leadingAttribute;
    }

    public void setLeadingAttribute(String leadingAttribute) {
        this.leadingAttribute = leadingAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RawSkill{" +
                "id=" + id +
                ", unskilled=" + unskilled +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", source='" + source + '\'' +
                ", leadingAttribute='" + leadingAttribute + '\'' +
                '}';
    }
}
