package edu.upc.dsa;

import edu.upc.dsa.BBDD.FactorySession;
import edu.upc.dsa.BBDD.Session;
import edu.upc.dsa.models.Ingrediente;
import edu.upc.dsa.models.Jugador;
import org.junit.Test;

public class ORMTest {


    @Test
    public void test() {
        Jugador j = new Jugador("Toni", "44444", "2222", "333", 100);
        Session s = FactorySession.openSession();
        s.save(j);
    }

    @Test
    public void test2() {
        Ingrediente in = new Ingrediente("Bacon", 2, 10);
        Session s = FactorySession.openSession();
        s.save(in);
    }
}
