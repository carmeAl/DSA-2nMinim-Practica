package edu.upc.dsa.models;

public class Partida {

    int id;
    int nivelActual;
    int puntos;
    int idMapa;
    int idJugador;
    public Partida(){    }

    public Partida(int id, int nivelActual, int puntos, int idMapa, int idJugador) {
        this.id = id;
        this.nivelActual = nivelActual;
        this.puntos = puntos;
        this.idMapa = idMapa;
        this.idJugador = idJugador;
    }

    public Partida(int nivelActual, int puntos, int idMapa, int idJugador) {
        this.nivelActual = nivelActual;
        this.puntos = puntos;
        this.idMapa = idMapa;
        this.idJugador = idJugador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getIdMapa() {
        return idMapa;
    }

    public void setIdMapa(int idMapa) {
        this.idMapa = idMapa;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
}
