package modelo;

import java.io.Serializable;

public class Subcategoria implements Serializable{
   private int id_subcategoria;
   private String nombre;
   private Categoria categoria;

    public Subcategoria() {
    }

    public Subcategoria(int id_subcategoria, String nombre, Categoria categoria) {
        this.id_subcategoria = id_subcategoria;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public Subcategoria(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
   
   
}
