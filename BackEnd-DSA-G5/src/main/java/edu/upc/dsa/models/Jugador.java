package edu.upc.dsa.models;

public class Jugador {
    private int id;
    private String nombre;
    private String password;
    private String email;
    private String pais;
    private double dinero;
    private int nivel;



    public Jugador() {
    }




    public Jugador (String nombre, String password, String email, String pais, double dinero) {
        this();
        this.setNombre(nombre);
        this.setPassword(password);
        this.setEmail(email);
        this.setPais(pais);
        this.setDinero(dinero);
    }

    //He hecho este nuevo constructor para poder poner nivel 1 en el register
    public void jugadorRegister (String nombre, String password, String email, String pais) {
        this.setNombre(nombre);
        this.setPassword(password);
        this.setEmail(email);
        this.setPais(pais);
        this.setDinero(0);
        this.nivel=1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public int getNivel() {return nivel;}
    public void setNivel(int nivel) {this.nivel = nivel;}

}
