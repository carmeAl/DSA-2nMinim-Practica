package edu.upc.dsa.models;


import edu.upc.dsa.util.RandomUtils;

public class Mapa {
    int id;
    String nombre;
    int numNiveles;

    public Mapa (){};

    public Mapa(int id, String nombre, int numNiveles) {
        this.id = id;
        this.nombre = nombre;
        this.numNiveles = numNiveles;
    }

    public Mapa(String nombreMapa, int numNivelesMapa) {
        this.nombre = nombreMapa;
        this.numNiveles = numNivelesMapa;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumNiveles() {
        return numNiveles;
    }

    public void setNumNiveles(int numNiveles) {
        this.numNiveles = numNiveles;
    }
}
