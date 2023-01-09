package edu.upc.dsa.services;
import edu.upc.dsa.DAO.PartidasManager;
import edu.upc.dsa.DAO.PartidasManagerImpl;
import edu.upc.dsa.models.Partida;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/partidas", description = "Endpoint to Partida Service")
@Path("/partidas")

public class PartidasService {

    private PartidasManager pm; //Gestor
    public PartidasService(){
        this.pm = PartidasManagerImpl.getInstance();
        if(pm.size()==0){
            this.pm.addPartida(1,5,0,0);
            this.pm.addPartida(1,50,1,1);
            this.pm.addPartida(2,100,2,2);
        }
    }

    @GET
    @ApiOperation(value = "get all Partidas", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/getAllPartidas")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllPartidas(){
        List<Partida> partidas = this.pm.getAllPartidas();
        GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(partidas) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get a Partida", notes = "hola2")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/{idPartida}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartida(@PathParam("idPartida") int id) {
        Partida t = this.pm.getPartida(id); //PM es un gestor, està a la capa Integration
        if (t == null) return Response.status(404).build(); //si no hi ha track
        else  return Response.status(201).entity(t).build(); // si hi ha track, empaqueto i envio amb un 201
    }

    @DELETE
    @ApiOperation(value = "delete a Partida", notes = "hola3")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/{idPartida}")
    public Response deletePartida(@PathParam("idPartida") int id) {
        Partida t = this.pm.getPartida(id);
        if (t == null) return Response.status(404).build();
        else this.pm.deletePartida(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Partida", notes = "hola4")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/putPartida")
    public Response putPartida(Partida partida) {

        Partida t = this.pm.updatePartida(partida);
        if (t == null) return Response.status(404).build();
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "create a new Partida", notes = "hola5")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Partida.class), //tot bé
            @ApiResponse(code = 500, message = "Validation Error") //error de validació

    })

    @Path("/postPartida")  //arriba una instancia/objecte buit, es crea automàticamente, no arriba un JSON
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPartida(Partida partida) {

        //if (partida.getIdPartida()==null)  return Response.status(500).entity(partida).build();
        this.pm.addPartida(partida);
        return Response.status(201).entity(partida).build();
    }

}