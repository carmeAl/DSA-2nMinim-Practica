package edu.upc.dsa.services;

import edu.upc.dsa.DAO.IngredienteManagerImpl;
import edu.upc.dsa.DAO.JugadorManager;
import edu.upc.dsa.DAO.JugadorManagerImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Api(value = "/Minimo2Service", description = "Servicios añadidos para practicar el minimo 2")
@Path("/Minimo2Service")
public class Minimo2Service {
    private FAQs fq;
    List<FAQs> list=new ArrayList<>();

    public Minimo2Service(){
        for(int i=1;i<10;i++){
            list.add(new FAQs("Pregunta"+i,"Respusta"+i));
        }

    }

    @GET
    @ApiOperation(value = "get all FAQs", notes = "hola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/Minimo2Service")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllFAQs(){
        //GenericEntity<Hashtable<String,String>> entity = new GenericEntity<Hashtable<String,String>>(FAQs) {};
        GenericEntity<List<FAQs>> entity = new GenericEntity<List<FAQs>>(list) {};
        //GenericEntity<List<String[]>> entity = new GenericEntity<List<String[]>>(Faqs) {};
        return Response.status(201).entity(entity).build()  ;
    }


}
