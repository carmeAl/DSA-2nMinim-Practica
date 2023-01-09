package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Ingrediente;
import edu.upc.dsa.models.IngredientesComprados;
import edu.upc.dsa.models.Jugador;

import java.sql.SQLException;
import java.util.List;

public interface IngredienteManager {
    public Ingrediente getIngrediente (int idIngrediente);
    public Ingrediente addIngrediente(String nombreIngrediente, int nivelDesbloqueoIngrediente, double precioIngrediente);
    public List<Ingrediente> getAllIngredientes();
    public void deleteIngrediente(int idIngrediente);
    public int getNivelDesbloqueoIngrediente(int idIngrediente);
    public double getPrecioIngrediente(int idIngrediente);
    public Ingrediente putIngrediente (Ingrediente ingrediente);
    //public int comprarIngrediente (Jugador j, int idIngrediente );
    public int postIngredienteComprado(IngredientesComprados ic, int idJugador, int idIngrediente);
    public List<Ingrediente> listaIngredientesComprados(int idJugador);


    public int size();
}
