package edu.upc.dsa.models;

public class LogIn {
    String nombre;
    String password;

    public LogIn() {}
    public LogIn(String name, String pass)
    {
        this.nombre =name;
        this.password =pass;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getPassword()
    {
        return password;
    }

    public void setNombre(String username){
        this.nombre = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
