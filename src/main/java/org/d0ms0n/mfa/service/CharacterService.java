package org.d0ms0n.mfa.service;

import org.d0ms0n.mfa.game.Dice;
import org.d0ms0n.mfa.model.Character;
import org.d0ms0n.mfa.model.*;
import org.d0ms0n.mfa.model.dto.Character1Dto;
import org.d0ms0n.mfa.model.helper.ArmourType;
import org.d0ms0n.mfa.model.helper.Profession;
import org.d0ms0n.mfa.model.helper.WeaponType;
import org.d0ms0n.mfa.repository.CharacterRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CharacterService {

    @Inject
    Dice dice;

    @Inject
    CharacterRepository characterRepository;

    public List<Character> getOwnCharacters(String userId) {
        return characterRepository.list("userId", userId);
    }

    public Character createCharacter(Character1Dto character1Dto, String userId) {
        String professionString = character1Dto.getProfession();
        List<String> attributeOrder = character1Dto.getAttributeOrder();
        Profession profession = Profession.getProfessionFromName(professionString);

        List<Integer> rolls = Arrays.asList(dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20());

        while (rolls.stream().mapToInt(Integer::intValue).sum() < 85) {
            rolls = Arrays.asList(dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20(), dice.r20());
        }
        rolls.sort(Collections.reverseOrder());

        Attributes attributes = new Attributes();

        for (int i = 0; i < attributeOrder.size(); i++) {
            String attribute = attributeOrder.get(i);
            switch (attribute.toLowerCase()) {
                case "strength":
                    attributes.setStrength(rolls.get(i));
                    break;
                case "dexterity":
                    attributes.setDexterity(rolls.get(i));
                    break;
                case "agility":
                    attributes.setAgility(rolls.get(i));
                    break;
                case "constitution":
                    attributes.setConstitution(rolls.get(i));
                    break;
                case "intelligence":
                    attributes.setIntelligence(rolls.get(i));
                    break;
                case "magictalent":
                    attributes.setMagicTalent(rolls.get(i));
                    break;
                case "charisma":
                    attributes.setCharisma(rolls.get(i));
                    break;
            }
        }

        Character character = new Character(userId,
                character1Dto.getName(),
                profession,
                0, attributes);
        character.setEquipment(collectStartItemsByProfession(profession));

        characterRepository.persist(character);
        return character;
    }

    private List<Item> collectStartItemsByProfession(Profession profession) {
        switch (profession) {
            case WARRIOR: {
                return Arrays.asList(
                        new Weapon("Longsword", "Longsword", 0, 0,
                                new RawWeapon(1, 6, 1, "Longsword", WeaponType.ONE_HANDED_SWORD), true),
                        new Weapon("Dagger", "Dagger", 0, 0,
                                new RawWeapon(1, 6, -1, "Dagger", WeaponType.STABBING_WEAPON), false),
                        new Weapon("Hand axe", "Hand axe", 0, 0,
                                new RawWeapon(1, 6, 0, "Hand axe", WeaponType.ONE_HANDED_STRIKING_WEAPON), false),
                        new ParryingWeapon("Tower shield", "Tower shield", 0, 0, 0,
                                new RawWeapon(0, 0, 0, "Tower shield", WeaponType.SHIELD), true),
                        new Armour(true, ArmourType.CHAIN)
                );
            }
            case ADVENTURER: {
                return Arrays.asList(
                        new Weapon("Longsword", "Longsword", 0, 0,
                                new RawWeapon(1, 6, 1, "Longsword", WeaponType.ONE_HANDED_SWORD), true),
                        new Weapon("Dagger", "Dagger", 0, 0,
                                new RawWeapon(1, 6, -1, "Dagger", WeaponType.STABBING_WEAPON), false),
                        new ParryingWeapon("Small shield", "Small shield", 2, 0, 0,
                                new RawWeapon(0, 0, 0, "Small shield", WeaponType.SHIELD), true),
                        new Armour(true, ArmourType.LEATHER)
                );
            }
            case BARBARIAN: {
                return Arrays.asList(
                        new Weapon("Barbarian battle axe", "Barbarian battle axe", 0, 0,
                                new RawWeapon(2, 6, 0, "Barbarian battle axe", WeaponType.TWO_HANDED_STRIKING_WEAPON), true),
                        new Weapon("Dagger", "Dagger", 0, 0,
                                new RawWeapon(1, 6, -1, "Dagger", WeaponType.STABBING_WEAPON), false),
                        new Weapon("Spear", "Spear", 0, 0,
                                new RawWeapon(0, 0, 0, "Spear", WeaponType.SPEAR_WEAPON), true),
                        new Armour(true, ArmourType.TEXTILE)
                );
            }
            case RANGER: {
                return Arrays.asList(
                        new Weapon("Longbow", "Longbow", 0, 0,
                                new RawWeapon(1, 6, 1, "Longbow", WeaponType.BOW), true),
                        new Weapon("Dagger", "Dagger", 0, 0,
                                new RawWeapon(1, 6, -1, "Dagger", WeaponType.STABBING_WEAPON), false),
                        new ParryingWeapon("Small shield", "Small shield", 2, 0, 0,
                                new RawWeapon(0, 0, 0, "Small shield", WeaponType.SHIELD), true),
                        new Armour(true, ArmourType.LEATHER)
                );
            }
            case THIEF: {
                return Arrays.asList(
                        new Weapon("Dagger", "Dagger", 0, 0,
                                new RawWeapon(1, 6, -1, "Dagger", WeaponType.STABBING_WEAPON), true),
                        new ParryingWeapon("Parrying dagger", "Parrying dagger", 2, 0, 0,
                                new RawWeapon(0, 0, 0, "Parrying dagger", WeaponType.PARRYING_WEAPON), true),
                        new Armour(true, ArmourType.LEATHER)
                );
            }
        }

        return null;
    }
}
