package edu.upc.dsa;

import edu.upc.dsa.models.Ingrediente;
import edu.upc.dsa.models.Jugador;
import edu.upc.dsa.util.ObjectHelper;
import org.junit.Assert;
import org.junit.Test;

public class ObjectHelperTest {


    @Test
    public void test(){
        Jugador j = new Jugador("Carme", "11111", "22222", "3333", 100);

        Assert.assertEquals("Carme", ObjectHelper.getter(j, "nombre"));

        ObjectHelper.setter(j, "nombre", "XXXX");

        Assert.assertEquals("XXXX", j.getNombre());
    }

    @Test
    public void test2(){
        Ingrediente in = new Ingrediente("Bacon", 2, 10);

        Assert.assertEquals("Bacon", ObjectHelper.getter(in, "nombreIngrediente"));

        ObjectHelper.setter(in, "nombreIngrediente", "XXXX");

        Assert.assertEquals("XXXX", in.getNombre());
    }


}
