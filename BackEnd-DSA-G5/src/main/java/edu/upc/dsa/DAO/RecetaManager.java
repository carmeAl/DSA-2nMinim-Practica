package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Receta;

import java.util.List;

public interface RecetaManager {
    public List<Receta> getAllRecetas();
    public Receta getReceta (int idReceta);
    public Receta addReceta(String nombre, int numPaso, double premioDinero, int premioPuntos);
    public void deleteReceta(int idReceta);
    public int getNumPaso(int idReceta);
    public double getPremioDinero(int idReceta);
    public int getPremioPuntos(int idReceta);

    public Receta putReceta (Receta receta);

    public int size();
}
