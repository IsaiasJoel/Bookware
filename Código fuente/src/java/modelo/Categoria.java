package modelo;

import java.io.Serializable;

public class Categoria implements Serializable{
    private int id_categoria;
    private String nombreCategoria;

    public Categoria(int id_categoria, String nombreCategoria) {
        this.id_categoria = id_categoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Categoria() {
    }

    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return this.id_categoria+" "+this.nombreCategoria;
    }
}
