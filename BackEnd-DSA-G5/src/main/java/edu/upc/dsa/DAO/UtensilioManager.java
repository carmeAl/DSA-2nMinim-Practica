package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Jugador;
import edu.upc.dsa.models.Utensilio;
import edu.upc.dsa.models.UtensiliosComprados;

import java.util.List;

public interface UtensilioManager {
    public List<Utensilio> getAllUtensilios();
    public Utensilio getUtensilio (int idUtensilio);
    public Utensilio addUtensilio(String nombreUtensilio, int tiempoNivel1, int tiempoNivel2, int tiempoNivel3, double precioUtensilio);
    public void deleteUtensilio(int idUtensilio);
    public int getTiempoNivel1(int idUtensilio);
    public int getTiempoNivel2(int idUtensilio);
    public int getTiempoNivel3(int idUtensilio);

    public Utensilio putUtensilio (Utensilio utensilio);

    public double getPrecioUtensilio(int idUtensilio);
    public int comprarUtensilio(Jugador j, int idUtensilio, int nivelUtensilio );
    public int postUtensilioComprado(int idJugador, int idUtensilio);

    public int size();


    List<UtensiliosComprados> listaUtensiliosComprados(int idJugador);
}
