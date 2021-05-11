package modelo;

import java.io.Serializable;

public class Perfil {
    private int id_perfil;
    private String nombre;
    private String descripcion;

    public Perfil() {
    }

    public Perfil(int id_perfil, String nombre, String descripcion) {
        this.id_perfil = id_perfil;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Perfil(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
