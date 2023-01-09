package edu.upc.dsa;

import edu.upc.dsa.DAO.RecetaManager;
import edu.upc.dsa.DAO.RecetaManagerImpl;
import edu.upc.dsa.models.Receta;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RecetaManagerTest {
    RecetaManager rm;

    @Before
    public void setUp(){
        rm = (RecetaManager) new RecetaManagerImpl();
        rm.addReceta("hamburguesa", 1, 2, 2);
        rm.addReceta("hamburguesa_lechuga", 1, 2, 2);
        rm.addReceta("hamburguesa_lechuga_tomate", 2, 2, 2);
    }

    @After
    public void tearDown() {this.rm = null;}

    @Test
    public void testAddReceta(){
        List<Receta> testRecetas = rm.getAllRecetas();
        Assert.assertEquals("hamburguesa", testRecetas.get(0).getNombre());
        Assert.assertEquals("0", testRecetas.get(0).getId());
        Assert.assertEquals(1, testRecetas.get(0).getNumPaso());
        Assert.assertEquals(2, testRecetas.get(0).getPremioDinero());
        Assert.assertEquals(2, testRecetas.get(0).getPremioPuntos());

        Assert.assertEquals("hamburguesa_lechuga", testRecetas.get(1).getNombre());
        Assert.assertEquals("1", testRecetas.get(1).getId());
        Assert.assertEquals(1, testRecetas.get(1).getNumPaso());
        Assert.assertEquals(2, testRecetas.get(1).getPremioDinero());
        Assert.assertEquals(2, testRecetas.get(1).getPremioPuntos());

        Assert.assertEquals("hamburguesa_lechuga_tomate", testRecetas.get(2).getNombre());
        Assert.assertEquals("2", testRecetas.get(2).getId());
        Assert.assertEquals(2, testRecetas.get(2).getNumPaso());
        Assert.assertEquals(2, testRecetas.get(2).getPremioDinero());
        Assert.assertEquals(2, testRecetas.get(2).getPremioPuntos());
    }
    @Test
    public void testDeleteReceta(){
        List<Receta> testRecetas = rm.getAllRecetas();
        Receta testReceta = testRecetas.get(0);
        rm.deleteReceta(testReceta.getId());
        Assert.assertEquals("hamburguesa", testRecetas.get(0).getNombre());
        Assert.assertEquals("0", testRecetas.get(0).getId());
        Assert.assertEquals(1, testRecetas.get(0).getNumPaso());
        Assert.assertEquals(2, testRecetas.get(0).getPremioDinero());
        Assert.assertEquals(2, testRecetas.get(0).getPremioPuntos());

        Assert.assertEquals("hamburguesa_lechuga", testRecetas.get(1).getNombre());
        Assert.assertEquals("1", testRecetas.get(1).getId());
        Assert.assertEquals(1, testRecetas.get(1).getNumPaso());
        Assert.assertEquals(2, testRecetas.get(1).getPremioDinero());
        Assert.assertEquals(2, testRecetas.get(1).getPremioPuntos());
    }
    @Test
    public void testPutReceta (){
        List<Receta> testRecetas = rm.getAllRecetas();
        Receta testReceta = testRecetas.get(1);
        testReceta.setPremioDinero(4);
        Receta receta = rm.putReceta(testReceta);

        Assert.assertEquals(receta.getNombre(), rm.getAllRecetas().get(1).getNombre());
        Assert.assertEquals(receta.getId(), rm.getAllRecetas().get(1).getId());
        Assert.assertEquals(receta.getNumPaso(), rm.getAllRecetas().get(1).getNumPaso());
        Assert.assertEquals(receta.getPremioDinero(), rm.getAllRecetas().get(1).getPremioDinero());
        Assert.assertEquals(receta.getPremioPuntos(), rm.getAllRecetas().get(1).getPremioPuntos());
    }
}
