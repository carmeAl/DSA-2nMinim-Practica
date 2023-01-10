package edu.upc.dsa.services;

import edu.upc.dsa.DAO.IngredienteManagerImpl;
import edu.upc.dsa.DAO.JugadorManager;
import edu.upc.dsa.DAO.JugadorManagerImpl;
import edu.upc.dsa.models.Jugador;
import edu.upc.dsa.models.LogIn;
import edu.upc.dsa.models.Registro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/jugador", description = "Endpoint to Partida Service")
@Path("/jugador")
public class JugadorService {

    private JugadorManager jm; //Gestor
    final static org.apache.log4j.Logger logger = Logger.getLogger(JugadorManagerImpl.class);

    public JugadorService(){
        this.jm = JugadorManagerImpl.getInstance();
        if(jm.size()==0){
            jm.addJugador("Juan","1111", "juan@gmail.com", "España", 100);
            jm.addJugador("Victoria","2222", "victoria@gmail.com", "España",100);
            jm.addJugador("Maria","3333", "maria@gmail.com", "Francia",100);
        }
    }


    @GET
    @ApiOperation(value = "get all Jugadores", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Jugador.class, responseContainer="List"),
    })
    @Path("/getAllJugadores")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllJugadores(){
        List<Jugador> jugadores = this.jm.getAllJugadores();
        GenericEntity<List<Jugador>> entity = new GenericEntity<List<Jugador>>(jugadores) {};
        return Response.status(201).entity(entity).build()  ;
    }


    @GET
    @ApiOperation(value = "get un Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Jugador.class),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    @Path("/{nombreJugador}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getJugadorByNombre(@PathParam("nombreJugador") String nombre) {
        Jugador j = this.jm.searchJugadorByName(nombre);
        if (j == null) return Response.status(404).build();
        else  return Response.status(201).entity(j).build();
    }


    @POST
    @ApiOperation(value = "registrar un nuevo Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error"),

    })
    @Path("/Register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Registre(Registro registropar) {
        if (registropar.getNombre()=="" || registropar.getPassword()=="") {
            return Response.status(500).build();
        }
        Jugador j = this.jm.registroJugador(registropar);
        if (j!=null){
            return Response.status(201).build();
        }
        return Response.status(500).build();
    }


    /*@DELETE
    @ApiOperation(value = "delete Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    @Path("/{idJugador}")
    public Response deleteJugador(@PathParam("idJugador") String id) {
        Jugador j = this.jm.getJugador(id);
        if (j == null) return Response.status(404).build();
        else {
            this.jm.deleteJugador(id);
            return Response.status(201).build();
        }
    }


    @PUT
    @ApiOperation(value = "update Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    @Path("/")
    public Response putJugador(Jugador jugador) {

        Jugador j = this.jm.putJugador(jugador);
        if (j == null) return Response.status(404).build();
        else return Response.status(201).build();
    }*/


    @POST
    @ApiOperation(value = "logIn Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Jugador.class),
            @ApiResponse(code = 404, message = "Jugador not found")

    })
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(LogIn loginpar) {
        logger.info("Web Login Usuario: "+loginpar.getNombre());
        logger.info("Web Login Password: "+loginpar.getPassword());
        Jugador j = this.jm.logInJugador(loginpar);
        if (j==null) {
            return Response.status(404).build();
        } else {
            return Response.status(201).entity(j).build();
        }
    }

    @PUT
    @ApiOperation(value = "update Pais Jugador", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Jugador not found")
    })
    @Path("/putJugador")
    public Response putJugador(Jugador jugador) {


        if (jugador == null) return Response.status(404).build();
        System.out.println(jugador.getPais());
        return Response.status(201).build();
    }
}
