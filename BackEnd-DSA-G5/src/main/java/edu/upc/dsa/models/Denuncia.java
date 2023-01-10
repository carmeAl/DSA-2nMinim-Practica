package edu.upc.dsa.models;

public class Denuncia {
    String fecha;
    String nombre;
    String comentario;

    public Denuncia(String fecha,String nombre, String comentario) {
        this.fecha=fecha;
        this.nombre = nombre;
        this.comentario = comentario;
    }
    public Denuncia() {

    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
