package edu.upc.dsa;


import edu.upc.dsa.DAO.UtensilioManager;
import edu.upc.dsa.DAO.UtensilioManagerImpl;
import edu.upc.dsa.models.Utensilio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UtensilioManagerTest {
    UtensilioManager um;

    @Before
    public void setUp(){
        um = (UtensilioManager) new UtensilioManagerImpl();
        um.addUtensilio("plancha", 15, 10, 5,10);
        um.addUtensilio("dispensador cocacola", 15, 10, 5,10);
        um.addUtensilio("horno", 15, 10, 5,10);
    }

    @After
    public void tearDown() {this.um = null;}

    @Test
    public void testAddUtensilio(){
        List<Utensilio> testUtensilios = um.getAllUtensilios();
        Assert.assertEquals("plancha", testUtensilios.get(0).getNombre());
        Assert.assertEquals("50", testUtensilios.get(0).getId());
        Assert.assertEquals(15, testUtensilios.get(0).getTiempoNivel1());
        Assert.assertEquals(10, testUtensilios.get(0).getTiempoNivel2());
        Assert.assertEquals(5, testUtensilios.get(0).getTiempoNivel3());

        Assert.assertEquals("dispensador cocacola", testUtensilios.get(1).getNombre());
        Assert.assertEquals("51", testUtensilios.get(1).getId());
        Assert.assertEquals(15, testUtensilios.get(1).getTiempoNivel1());
        Assert.assertEquals(10, testUtensilios.get(1).getTiempoNivel2());
        Assert.assertEquals(5, testUtensilios.get(1).getTiempoNivel3());

        Assert.assertEquals("horno", testUtensilios.get(2).getNombre());
        Assert.assertEquals("54", testUtensilios.get(2).getId());
        Assert.assertEquals(15, testUtensilios.get(2).getTiempoNivel1());
        Assert.assertEquals(10, testUtensilios.get(2).getTiempoNivel2());
        Assert.assertEquals(5, testUtensilios.get(2).getTiempoNivel3());
    }
    @Test
    public void testDeleteUtensilio(){
        List<Utensilio> testUtensilios = um.getAllUtensilios();
        Utensilio testUtensilio = testUtensilios.get(0);
        um.deleteUtensilio(testUtensilio.getId());
        Assert.assertEquals("plancha", testUtensilios.get(0).getNombre());
        Assert.assertEquals("50", testUtensilios.get(0).getId());
        Assert.assertEquals(15, testUtensilios.get(0).getTiempoNivel1());
        Assert.assertEquals(10, testUtensilios.get(0).getTiempoNivel2());
        Assert.assertEquals(5, testUtensilios.get(0).getTiempoNivel3());

        Assert.assertEquals("dispensador cocacola", testUtensilios.get(1).getNombre());
        Assert.assertEquals("51", testUtensilios.get(1).getId());
        Assert.assertEquals(15, testUtensilios.get(1).getTiempoNivel1());
        Assert.assertEquals(10, testUtensilios.get(1).getTiempoNivel2());
        Assert.assertEquals(5, testUtensilios.get(1).getTiempoNivel3());
    }
    @Test
    public void testPutUtensilio (){
        List<Utensilio> testUtensilios = um.getAllUtensilios();
        Utensilio testUtensilio = testUtensilios.get(1);
        testUtensilio.setTiempoNivel1(20);
        Utensilio utensilio = um.putUtensilio(testUtensilio);

        Assert.assertEquals(utensilio.getNombre(), um.getAllUtensilios().get(1).getNombre());
        Assert.assertEquals(utensilio.getId(), um.getAllUtensilios().get(1).getId());
        Assert.assertEquals(utensilio.getTiempoNivel1(), um.getAllUtensilios().get(1).getTiempoNivel1());
        Assert.assertEquals(utensilio.getTiempoNivel2(), um.getAllUtensilios().get(1).getTiempoNivel2());
        Assert.assertEquals(utensilio.getTiempoNivel3(), um.getAllUtensilios().get(1).getTiempoNivel3());
    }
}
