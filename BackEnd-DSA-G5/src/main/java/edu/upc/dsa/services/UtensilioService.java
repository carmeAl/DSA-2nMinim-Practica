package edu.upc.dsa.services;

import edu.upc.dsa.DAO.UtensilioManager;
import edu.upc.dsa.DAO.UtensilioManagerImpl;
import edu.upc.dsa.models.Utensilio;
import edu.upc.dsa.models.UtensiliosComprados;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/utensilio", description = "Endpoint to Partida Service")
@Path("/utensilio")
public class UtensilioService {

    private UtensilioManager um; //Gestor

    public UtensilioService(){
        this.um = UtensilioManagerImpl.getInstance();
        if(um.size()==0) {
            um.addUtensilio("Plancha", 15, 10, 5, 15);
            um.addUtensilio("DispensadorCocacola", 15, 10, 5, 15);
            um.addUtensilio("Cafetera", 15, 10, 5, 15);
            um.addUtensilio("Dispensador", 15, 10, 5, 15);
            um.addUtensilio("Horno", 15, 10, 5, 15);
        }
    }


    @GET
    @ApiOperation(value = "get all Utensilios", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Utensilio.class, responseContainer="List"),
    })
    @Path("/getAllUtensilios")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllUtensilios(){
        try {
            List<Utensilio> listUtensilios = this.um.getAllUtensilios();
            GenericEntity<List<Utensilio>> entity = new GenericEntity<List<Utensilio>>(listUtensilios) {};
            return Response.status(200).entity(entity).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(401).build();
        }
    }


    @GET
    @ApiOperation(value = "get un Utensilio", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Utensilio.class),
            @ApiResponse(code = 404, message = "Utensilio not found")
    })
    @Path("/getUtensilio/{idUtensilio}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getUtensilio(@PathParam("idUtensilio") int id) {
        Utensilio u = this.um.getUtensilio(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }


    @POST
    @ApiOperation(value = "añadir un nuevo Utensilio", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error"),

    })
    @Path("/postUtensilio")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUtensilio(Utensilio utensilio) {
        if (utensilio.getNombre()=="" || utensilio.getTiempoNivel1()==0|| utensilio.getTiempoNivel2()==0||utensilio.getTiempoNivel3()==0) {
            return Response.status(500).build();
        }
        Utensilio u = this.um.addUtensilio(utensilio.getNombre(),utensilio.getTiempoNivel1(),utensilio.getTiempoNivel2(),utensilio.getTiempoNivel3(), utensilio.getPrecio());
        if (u!=null){
            return Response.status(201).build();
        }
        return Response.status(500).build();
    }


    @DELETE
    @ApiOperation(value = "delete Utensilio", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Utensilio not found")
    })
    @Path("/deleteUtensilio/{idUtensilio}")
    public Response deleteUtensilio(@PathParam("idUtensilio") int id) {
        Utensilio u = this.um.getUtensilio(id);
        if (u == null) return Response.status(404).build();
        else {
            this.um.deleteUtensilio(id);
            return Response.status(201).build();
        }
    }

    /*
    @POST
    @ApiOperation(value = "añadir Utensilio Comprado BBDD", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error"),

    })
    @Path("/postUtensilioComprado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUtensilioComprado(UtensiliosComprados uc) {
        if (uc.getIdUtensilio()==0 || uc.getIdJugador()==0 || uc.getNivel()==0) {
            return Response.status(500).build();
        }
        UtensiliosComprados UtCom=this.um.postUtensilioComprado(uc,uc.getIdJugador(),uc.getIdUtensilio());
        if (UtCom!=null){
            return Response.status(201).build();
        }
        return Response.status(500).build();
    }*/

    @PUT
    @ApiOperation(value = "Actualizar Utensilio Mejorado a BBDD", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No se encuentra coincidencias en la BBDD"),
            @ApiResponse(code = 501, message = "Dinero insuficiente"),
            @ApiResponse(code = 502, message = "Nivel Maximo conseguido"),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/postUtensilioComprado/{idJugador}{idUtensilio}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUtensilioComprado(@PathParam("idJugador") int idJugador,@PathParam("idUtensilio") int idUtensilio) {
        if (idJugador==0 || idUtensilio==0 ) {
            return Response.status(500).build();
        }
        int code=this.um.postUtensilioComprado(idJugador,idUtensilio);
        return Response.status(code).build();
    }


    @PUT
    @ApiOperation(value = "update Utensilio", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Utensilio not found")
    })
    @Path("/putUtensilio")
    public Response putUtensilio(Utensilio utensilio) {

        Utensilio u = this.um.putUtensilio(utensilio);
        if (u == null) return Response.status(404).build();
        else return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Lista Utensilios de un Jugador", notes = "lista con los Utensilios que tiene un jugador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "utensilio not found")

    })

    @Path("/getLista/{idJugador}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma BuyedObject in a List
    public Response listaUtensiliosComprados(@PathParam("idJugador") int idJugador) {
        try {
            List<UtensiliosComprados> UtensiliosCompradosPorJugador = this.um.listaUtensiliosComprados(idJugador);
            if (UtensiliosCompradosPorJugador== null) {
                return Response.status(401).build();
            }
            GenericEntity<List<UtensiliosComprados>> entity = new GenericEntity<List<UtensiliosComprados>>(UtensiliosCompradosPorJugador) {};
            return Response.status(200).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).build();
        }

    }
}

