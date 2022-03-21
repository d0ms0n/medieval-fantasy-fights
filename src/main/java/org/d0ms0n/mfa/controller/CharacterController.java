package org.d0ms0n.mfa.controller;

import org.d0ms0n.mfa.model.Character;
import org.d0ms0n.mfa.model.dto.Character1Dto;
import org.d0ms0n.mfa.model.helper.Profession;
import org.d0ms0n.mfa.service.CharacterService;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/characters")
public class CharacterController {

    @Inject
    CharacterService characterService;

    @Inject
    @Claim(standard = Claims.sub)
    String userId;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCharacter(Character1Dto character1Dto) {

        String professionString = character1Dto.getProfession();
//        String gender = character1Dto.getGender().toLowerCase();
        List<String> attributeOrder = character1Dto.getAttributeOrder();

        if (Profession.getProfessionFromName(professionString) == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("profession unknown").build();
        }
//        if (!gender.equals("female") && !gender.equals("male")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("gender unknown").build();
//        }
        if (attributeOrder == null || attributeOrder.size() != 7) {
            return Response.status(Response.Status.BAD_REQUEST).entity("too many attributes").build();
        }

        Character character = characterService.createCharacter(character1Dto, userId);
        return Response.created(null).entity(character).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Character> listCharacters() {
        return characterService.getOwnCharacters(userId);
    }
}
