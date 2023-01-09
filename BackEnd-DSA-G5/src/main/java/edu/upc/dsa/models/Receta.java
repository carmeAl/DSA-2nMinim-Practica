package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Receta {

    int id;
    String nombre;
    int numPaso;
    double premioDinero;
    int premioPuntos;
    public Receta() {

    }

    public Receta(int id, String nombre, int numPaso, double premioDinero, int premioPuntos) {
        this.id = id;
        this.nombre = nombre;
        this.numPaso = numPaso;
        this.premioDinero = premioDinero;
        this.premioPuntos = premioPuntos;
    }

    public Receta(String nombre, int numPaso, double premioDinero, int premioPuntos) {
        this.nombre = nombre;
        this.numPaso = numPaso;
        this.premioDinero = premioDinero;
        this.premioPuntos = premioPuntos;
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

    public int getNumPaso() {
        return numPaso;
    }

    public void setNumPaso(int numPaso) {
        this.numPaso = numPaso;
    }

    public double getPremioDinero() {
        return premioDinero;
    }

    public void setPremioDinero(double premioDinero) {
        this.premioDinero = premioDinero;
    }

    public int getPremioPuntos() {
        return premioPuntos;
    }

    public void setPremioPuntos(int premioPuntos) {
        this.premioPuntos = premioPuntos;
    }
}
