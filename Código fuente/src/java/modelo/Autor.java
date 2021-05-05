package modelo;

import java.io.Serializable;

public class Autor implements Serializable{
    
    private int id_autor;
    private String nombres;
    private String apellidos;
    private String nacionalidad;

    public Autor() {
    }

    public Autor(String nombres, String apellidos, String nacionalidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    public Autor(int id_autor, String nombres, String apellidos, String nacionalidad) {
        this.id_autor = id_autor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }
    
    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return this.nombres + " " + this.apellidos;
    }
}
