package com.example.service;

import com.example.model.Tank;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tanks")
public class TankService {
    private static List<Tank> tanks = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tank> getAllTanks() {
        return tanks;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTank(Tank tank) {
        tanks.add(tank);
        return Response.ok(tank).build();
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTank(@PathParam("name") String name, Tank updatedTank) {
        for (Tank tank : tanks) {
            if (tank.getName().equalsIgnoreCase(name)) {
                tank.setCountry(updatedTank.getCountry());
                tank.setGunCaliber(updatedTank.getGunCaliber());
                tank.setFrontArmor(updatedTank.getFrontArmor());
                tank.setSpeed(updatedTank.getSpeed());
                tank.setCrew(updatedTank.getCrew());
                return Response.ok(tank).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
