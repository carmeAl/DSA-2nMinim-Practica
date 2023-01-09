package edu.upc.dsa.services;

import edu.upc.dsa.DAO.MapaManager;
import edu.upc.dsa.DAO.MapaManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/mapa", description = "Endpoint to Partida Service")
@Path("/mapa")
public class MapaService {

    private MapaManager mm; //Gestor

    public MapaService(){
        this.mm = MapaManagerImpl.getInstance();
        if(mm.getListMapas().size()==0) {
            this.mm.postMapa("Pizzeria", 3);
            this.mm.postMapa("Hamburgeseria", 3);
            this.mm.postMapa("Pasteleria", 3);
            this.mm.postMapa("Calle", 3);
        }
    }


    @GET
    @ApiOperation(value = "get all Mapas", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mapa.class, responseContainer="List"),
    })
    @Path("/getAllMapas")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllMapas(){
        HashMap<Integer,Mapa> listMapas = this.mm.getAllMapas();

        // Getting Collection of values from HashMap
        Collection<Mapa> values = listMapas.values();

        // Creating an ArrayList of values
        ArrayList<Mapa> listOfValues
                = new ArrayList<>(values);
        GenericEntity<List<Mapa>> entity = new GenericEntity<List<Mapa>>(listOfValues) {};
        return Response.status(201).entity(entity).build()  ;
    }


    @GET
    @ApiOperation(value = "get un Mapa", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mapa.class),
            @ApiResponse(code = 404, message = "Mapa not found")
    })
    @Path("/getMapa/{idMapa}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getMapa(@PathParam("idMapa") int id) {
        Mapa m = this.mm.getMapa(id);
        if (m == null) return Response.status(404).build();
        else  return Response.status(201).entity(m).build();
    }


    @POST
    @ApiOperation(value = "añadir un nuevo Mapa", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error"),

    })
    @Path("/postMapa")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMapa(Mapa mapa) {
        if (mapa.getNombre()=="" || mapa.getNumNiveles()==0) {
            return Response.status(500).build();
        }
        Mapa m = this.mm.postMapa(mapa.getNombre(),mapa.getNumNiveles());
        if (m!=null){
            return Response.status(201).build();
        }
        return Response.status(500).build();
    }


    @DELETE
    @ApiOperation(value = "delete Mapa", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Mapa not found")
    })
    @Path("/deleteMapa/{idMapa}")
    public Response deleteMapa(@PathParam("idMapa") int id) {
        Mapa m = this.mm.getMapa(id);
        if (m == null) return Response.status(404).build();
        else {
            this.mm.deleteMapa(id);
            return Response.status(201).build();
        }
    }


    @PUT
    @ApiOperation(value = "update Mapa", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Mapa not found")
    })
    @Path("/putMapa")
    public Response putMapa(Mapa mapa) {

        Mapa m = this.mm.putMapa(mapa.getId(),mapa.getNombre(),mapa.getNumNiveles());
        if (m == null) return Response.status(404).build();
        else return Response.status(201).build();
    }
}

