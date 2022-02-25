package org.d0ms0n.midgard.importer;

import io.restassured.path.json.JsonPath;
import org.d0ms0n.midgard.arena.game.CombatEngine;
import org.d0ms0n.midgard.arena.model.Character;
import org.d0ms0n.midgard.arena.model.*;
import org.d0ms0n.midgard.arena.model.helper.*;
import org.d0ms0n.midgard.arena.repository.RawSkillRepository;
import org.d0ms0n.midgard.arena.repository.RawWeaponRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Path("characters/import")
public class MoamImporter {

    public static Logger log = LoggerFactory.getLogger(MoamImporter.class);

    @Inject
    CharacterBuilder characterBuilder;

    @Inject
    CombatEngine combatEngine;

    @Inject
    RawSkillRepository skillRepository;

    @Inject
    RawWeaponRepository weaponRepository;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importCharacters(@MultipartForm MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<String> fileNames = new ArrayList<>();

        List<InputPart> inputParts = uploadForm.get("file");
        System.out.println("inputParts size: " + inputParts.size());
        String fileName;

        List<Character> importedChars = new ArrayList<>();
        for (InputPart inputPart : inputParts) {
            MultivaluedMap<String, String> header = inputPart.getHeaders();
            fileName = getFileName(header);
            fileNames.add(fileName);
            System.out.println("File Name: " + fileName);
            InputStream inputStream = inputPart.getBody(InputStream.class, null);
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            importedChars.add(importChar(json));
        }

        if (importedChars.size() == 2) {
            Squad squad1 = new Squad("Team 1", List.of(importedChars.get(0)));
            Squad squad2 = new Squad("Team 2", List.of(importedChars.get(1)));
            combatEngine.start(List.of(squad1, squad2));
        }

        String uploadedFileNames = String.join(", ", fileNames);
        return Response.ok().entity("All files " + uploadedFileNames + " successfully.").build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        return "unknown";
    }

//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response importCharacter(@MultipartForm MultipartBody data) throws IOException {
//        String json = new String(data.file.readAllBytes(), StandardCharsets.UTF_8);
//        return Response.status(Response.Status.OK).entity(importChar(json)).build();
//    }

    private Character importChar(String json) {
        JsonPath c = JsonPath.from(json);
        int grad = c.getInt("grad");
        Race race = Race.getRaceFromName(c.getString("rasse"));
        Profession profession = Profession.getProfessionFromName(c.getString("typ"));
        int height = c.getInt("groesse");
        int weight = c.getInt("gewicht");

        Attributes attributes = new Attributes(
                c.get("eigenschaften.st"),
                c.get("eigenschaften.gs"),
                c.get("eigenschaften.gw"),
                c.get("eigenschaften.ko"),
                c.get("eigenschaften.in"),
                c.get("eigenschaften.zt"),
                c.get("eigenschaften.pa"),
                c.get("eigenschaften.au"),
                c.get("eigenschaften.wk")
        );

        // import skills to database
        List<RawSkill> rawSkills = new ArrayList<>();
        List<Object> fertigkeiten = c.getList("fertigkeiten");
        fertigkeiten.addAll(c.getList("waffenfertigkeiten"));
        fertigkeiten.forEach(skill -> {
            HashMap<String, Object> rawSkill = (HashMap<String, Object>) skill;
            RawSkill s = skillRepository.findByName((String) rawSkill.get("name"));
            if (s == null) {
                skillRepository.persist(new RawSkill(0, (String) rawSkill.get("name"), null, (String) rawSkill.get("quelle"), ""));
            }
        });

        // import weapons to database
        List<Object> weapons = c.getList("waffen");
        List<Item> weaponList = new ArrayList<>();
        weapons.forEach(weapon -> {
            HashMap<String, Object> rawWeapon = (HashMap<String, Object>) weapon;
            RawWeapon s = weaponRepository.findByWeaponName((String) rawWeapon.get("nameFuerSpezialisierung"));
            if (s == null) {
                weaponRepository.persist(
                        new RawWeapon(
                                1,
                                6,
                                0,
                                (String) rawWeapon.get("nameFuerSpezialisierung"),
                                null
                        ));
            } else {
                // add weapons to characters
                Weapon weaponItem = new Weapon(
                        (String) rawWeapon.get("name"),
                        (String) rawWeapon.get("beschreibung"),
                        (Float) rawWeapon.get("gewicht"),
                        (Float) rawWeapon.get("wert"),
                        s,
                        false
                );
                weaponItem.setMagicalAttackBonus((int) rawWeapon.get("anb"));
                weaponItem.setMagicalDamageBonus((int) rawWeapon.get("schb"));
                weaponItem.setMagicalDefenseBonus((int) rawWeapon.get("abwb"));
                weaponList.add(weaponItem);
            }
        });

        return characterBuilder
                .withName(c.getString("name"))
                .withLevel(grad)
                .withExperience(c.get("erfahrungsschatz.value"))
                .withAge(c.getInt("alter"))
                .withFaith(c.getString("glaube"))
                .withHeightInCm(height)
                .withWeightInKg(weight)
                .withHeightType(HeightType.getHeightTypeFromSeize(race, height))
                .withWidthType(WidthType.getWidthTypeFromSizeAndWeight(race, height, weight))
                .withLifePointsMax(c.get("lp.max"))
                .withLifePoints(c.get("lp.value"))
                .withStaminaPointsMax(c.get("ap.max"))
                .withStaminaPoints(c.get("ap.value"))
                .withHanded(c.getString("hand"))
                .withSpecialisations(c.get("spezialisierung"))
                .withAttributes(attributes)
                .withMovement(c.get("b.max"))
                .withRace(race)
                .withEquipment(weaponList)
                .withProfession(profession)
                .withCharacteristics(new Characteristics(c.get("merkmale.haarfarbe"), c.get("merkmale.augenfarbe"), c.get("merkmale.sonstiges")))
                .withBennies(new Bennies(c.get("bennies.sg"), c.get("bennies.gg"), c.get("bennies.gp")))
                .build();
    }

}
