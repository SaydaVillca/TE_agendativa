package com.emergentes.modelo;
public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String correo;

    public Contacto() {
        this.id = 0;
        this.nombre = "";
        this.telefono = "";
        this.correo = "";
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
