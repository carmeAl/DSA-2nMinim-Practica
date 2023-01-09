package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Mapa;

import java.util.HashMap;

public interface MapaManager {
    public HashMap<Integer, Mapa> getListMapas();
    public void setListMapas(HashMap<Integer, Mapa> listMapas);
    public HashMap<Integer,Mapa> getAllMapas();
    public Mapa getMapa(int idMapa);
    public Mapa postMapa( String nombreMapa, int numNivelesMapa);
    public void deleteMapa(int idMapa);
    public Mapa putMapa(int idMapa, String nombreMapa, int numNivelesMapa);
}
