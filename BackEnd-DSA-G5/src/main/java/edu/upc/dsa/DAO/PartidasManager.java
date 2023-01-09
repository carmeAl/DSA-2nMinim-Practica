package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Partida;

import java.util.List;

public interface PartidasManager {

    //public List<Partida> getAllPartidasJugador(int idJugador);
    //public List<Partida> getAllPartidasMapa(int idMapa);
    public List<Partida> getAllPartidas();
    public Partida getPartida(int idPartida);
    public Partida addPartida(Partida p);
    public Partida addPartida (int nivelActual, int puntos, int idMapa, int idJugador);
    public void deletePartida(int idPartida);
    public Partida updatePartida(Partida p);
    public int size();

}
