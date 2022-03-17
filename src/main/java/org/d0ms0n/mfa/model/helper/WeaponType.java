package org.d0ms0n.mfa.model.helper;

public enum WeaponType {
    ONE_HANDED_STRIKING_WEAPON("Einhandschlagwaffen"),
    ONE_HANDED_SWORD("Einhandschwerter"),
    FENCING_WEAPON("Fechtwaffen"),
    CHAIN_WEAPON("Kettenwaffen"),
    SPEAR_WEAPON("Spießwaffen"),
    STABBING_WEAPON("Stichwaffen"),
    STICK_WEAPON("Stabwaffen"),
    MAGIC_WAND("Magierstäbe"),
    TWO_HANDED_STRIKING_WEAPON("Zweihandschlagwaffen"),
    TWO_HANDED_SWORD("Zweihandschwerter"),
    WEAPONLESS_COMBAT("Waffenloser Kampf"),
    POLE_THROWING_WEAPON("Stielwurfwaffen"),
    THROWING_BLADE("Wurfklingen"),
    THROWING_DISC("Wurfscheiben"),
    THROWING_SPEAR("Wurfspieße"),
    CROSSBOW("Armbrüste"),
    BLOWPIPE("Blasrohre"),
    BOW("Bögen"),
    SLINGSHOT("Schleudern"),
    SHIELD("Schilde"),
    PARRYING_WEAPON("Parierwaffen"),
    UNSKILLED("Bloße Hand");

    String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static WeaponType getTypeByName(String name) {
        return WeaponType.valueOf(name);
    }
}
