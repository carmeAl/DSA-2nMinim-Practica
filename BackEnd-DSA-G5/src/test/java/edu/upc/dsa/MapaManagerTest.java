package edu.upc.dsa;
import edu.upc.dsa.DAO.MapaManager;
import edu.upc.dsa.DAO.MapaManagerImpl;
import edu.upc.dsa.models.Mapa;
import io.swagger.models.auth.In;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;


public class MapaManagerTest {
    MapaManager mm;
    HashMap<Integer,Mapa>ListMapa;
    @Before
    public void setUp(){
        mm=new MapaManagerImpl();
        mm.postMapa("Hamburgueseria",3);
        mm.postMapa("Pizzeria",4);
        mm.postMapa("Pasteleria",5);

    }

    @After
    public void tearDown(){this.mm=null;}

    @Test
    public void testGetAllMapas(){
        this.ListMapa= mm.getAllMapas();
        Assert.assertEquals(3,ListMapa.size());
        Assert.assertEquals("1",ListMapa.get("1").getId());
        Assert.assertEquals("Hamburgueseria",ListMapa.get("1").getNombre());
        Assert.assertEquals(3,ListMapa.get("1").getNumNiveles());
        Assert.assertEquals("2",ListMapa.get("2").getId());
        Assert.assertEquals("Pizzeria",ListMapa.get("2").getNombre());
        Assert.assertEquals(4,ListMapa.get("2").getNumNiveles());
        Assert.assertEquals("3",ListMapa.get("3").getId());
        Assert.assertEquals("Pasteleria",ListMapa.get("3").getNombre());
        Assert.assertEquals(5,ListMapa.get("3").getNumNiveles());

    }

    @Test
    public void testGetMapa(){
        Mapa mapa=mm.getMapa(1);
        Assert.assertEquals("1",mapa.getId());
        Assert.assertEquals("Hamburgueseria",mapa.getNombre());
        Assert.assertEquals(3,mapa.getNumNiveles());

        mapa=mm.getMapa(2);
        Assert.assertEquals("2",mapa.getId());
        Assert.assertEquals("Pizzeria",mapa.getNombre());
        Assert.assertEquals(4,mapa.getNumNiveles());

        mapa=mm.getMapa(3);
        Assert.assertEquals("3",mapa.getId());
        Assert.assertEquals("Pasteleria",mapa.getNombre());
        Assert.assertEquals(5,mapa.getNumNiveles());

        mapa=mm.getMapa(4);
        Assert.assertEquals(null,mapa);
    }

    @Test
    public void testDeleteMapa(){
        mm.deleteMapa(1);
        this.ListMapa= mm.getAllMapas();
        Assert.assertEquals(2,ListMapa.size());
        Assert.assertEquals("2",ListMapa.get("2").getId());
        Assert.assertEquals("Pizzeria",ListMapa.get("2").getNombre());
        Assert.assertEquals(4,ListMapa.get("2").getNumNiveles());
        Assert.assertEquals("3",ListMapa.get("3").getId());
        Assert.assertEquals("Pasteleria",ListMapa.get("3").getNombre());
        Assert.assertEquals(5,ListMapa.get("3").getNumNiveles());

        mm.deleteMapa(2);
        this.ListMapa= mm.getAllMapas();
        Assert.assertEquals(1,ListMapa.size());
        Assert.assertEquals("3",ListMapa.get("3").getId());
        Assert.assertEquals("Pasteleria",ListMapa.get("3").getNombre());
        Assert.assertEquals(5,ListMapa.get("3").getNumNiveles());

        mm.deleteMapa(4);
        //En la terminal vemos el warning de que no ha encontrado el mapa
    }

    @Test
    public void testPutMapa(){
        mm.putMapa(1,"Italiano",6);
        Mapa mapa=mm.getMapa(1);
        Assert.assertEquals(3,mm.getListMapas().size());
        Assert.assertEquals("1",mapa.getId());
        Assert.assertEquals("Italiano",mapa.getNombre());
        Assert.assertEquals(6,mapa.getNumNiveles());
    }
}
