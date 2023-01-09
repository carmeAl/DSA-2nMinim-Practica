package edu.upc.dsa;

import edu.upc.dsa.DAO.IngredienteManager;
import edu.upc.dsa.DAO.JugadorManagerImpl;
import edu.upc.dsa.models.Ingrediente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class IngredienteManagerTest {

    IngredienteManager im;

    @Before
    public void setUp(){
        im = (IngredienteManager) new JugadorManagerImpl();
        im.addIngrediente("masa pizza",1, 0 );
        im.addIngrediente("mozarella", 1, 0);
        im.addIngrediente("jamon", 2, 0);
    }

    @After
    public void tearDown() {this.im = null;}

    @Test
    public void testAddIngrediente(){
        List<Ingrediente> testIngredientes = im.getAllIngredientes();
        Assert.assertEquals("masa pizza", testIngredientes.get(0).getNombre());
        Assert.assertEquals("1", testIngredientes.get(0).getId());
        Assert.assertEquals(1, testIngredientes.get(0).getNivelDesbloqueo());
        Assert.assertEquals(0, testIngredientes.get(0).getPrecio());

        Assert.assertEquals("mozarella", testIngredientes.get(1).getNombre());
        Assert.assertEquals("2", testIngredientes.get(1).getId());
        Assert.assertEquals(1, testIngredientes.get(1).getNivelDesbloqueo());
        Assert.assertEquals(0, testIngredientes.get(1).getPrecio());

        Assert.assertEquals("jamon", testIngredientes.get(2).getNombre());
        Assert.assertEquals("5", testIngredientes.get(2).getId());
        Assert.assertEquals(2, testIngredientes.get(2).getNivelDesbloqueo());
        Assert.assertEquals(0, testIngredientes.get(2).getPrecio());
    }
    @Test
    public void testDeleteIngrediente(){
        List<Ingrediente> testIngredientes = im.getAllIngredientes();
        Ingrediente testIngrediente = testIngredientes.get(0);
        im.deleteIngrediente(testIngrediente.getId());
        Assert.assertEquals("masa pizza", testIngredientes.get(0).getNombre());
        Assert.assertEquals("1", testIngredientes.get(0).getId());
        Assert.assertEquals(1, testIngredientes.get(0).getNivelDesbloqueo());
        Assert.assertEquals(0, testIngredientes.get(0).getPrecio());

        Assert.assertEquals("mozarella", testIngredientes.get(1).getNombre());
        Assert.assertEquals("2", testIngredientes.get(1).getId());
        Assert.assertEquals(1, testIngredientes.get(1).getNivelDesbloqueo());
        Assert.assertEquals(0, testIngredientes.get(1).getPrecio());
    }
    @Test
    public void testPutIngrediente (){
        List<Ingrediente> testIngredientes = im.getAllIngredientes();
        Ingrediente testIngrediente = testIngredientes.get(1);
        testIngrediente.setPrecio(1);
        Ingrediente ingrediente = im.putIngrediente(testIngrediente);

        Assert.assertEquals(ingrediente.getNombre(), im.getAllIngredientes().get(1).getNombre());
        Assert.assertEquals(ingrediente.getId(), im.getAllIngredientes().get(1).getId());
        Assert.assertEquals(ingrediente.getNivelDesbloqueo(), im.getAllIngredientes().get(1).getNivelDesbloqueo());
        Assert.assertEquals(ingrediente.getPrecio(), im.getAllIngredientes().get(1).getPrecio());
    }
}
