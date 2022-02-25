package org.d0ms0n.mfa.arena.controller;

import org.d0ms0n.mfa.arena.model.RawWeapon;
import org.d0ms0n.mfa.arena.repository.RawWeaponRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("weapons")
public class WeaponController {

    @Inject
    RawWeaponRepository weaponRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RawWeapon> getWeapons() {
        return weaponRepository.findAll().stream().collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWeapon(RawWeapon weapon) {
        if (weapon == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (weaponRepository.findByWeaponName(weapon.getWeaponName()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        weaponRepository.persist(weapon);
        return Response.created(URI.create("/weapons/" + weapon.getId())).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateWeapon(@PathParam("id") String id, RawWeapon weapon) {
        if (weapon == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (weaponRepository.findById(weapon.getId()) == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        weaponRepository.update(weapon);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteWeapon(@PathParam("id") String id, RawWeapon weapon) {
        if (weapon == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (weaponRepository.findById(weapon.getId()) == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        weaponRepository.delete(weapon);
        return Response.noContent().build();
    }
}
