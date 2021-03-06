package org.d0ms0n.mfa.controller;

import io.quarkus.security.identity.SecurityIdentity;
import org.d0ms0n.mfa.model.RawSkill;
import org.d0ms0n.mfa.repository.RawSkillRepository;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("skills")
@RolesAllowed("arena-user")
public class SkillController {

    @Inject
    RawSkillRepository skillRepository;

    @Inject
    SecurityIdentity identity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RawSkill> getSkills() {
        identity.getPrincipal().getName();
        return skillRepository.findAll().stream().collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSkill(RawSkill skill) {
        if (skill == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (skillRepository.findByName(skill.getName()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        skillRepository.persist(skill);
        return Response.created(URI.create("/skills/" + skill.getId())).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSkill(@PathParam("id") String id, RawSkill skill) {
        if (skill == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (skillRepository.findById(skill.getId()) == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        skillRepository.update(skill);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSkill(@PathParam("id") String id, RawSkill skill) {
        if (skill == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (skillRepository.findById(skill.getId()) == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        skillRepository.delete(skill);
        return Response.noContent().build();
    }
}
