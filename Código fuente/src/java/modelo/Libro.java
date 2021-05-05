package modelo;

import java.io.Serializable;

public class Libro implements Serializable{
    private int id_libro;
    private String titulo;
    private int stock;
    private double precioSugerido;
    private double ganancia;
    private Autor autor;
    private Editorial editorial;
    private Categoria categoria;

    public Libro(int id_libro, String titulo, int stock, double precioSugerido, double ganancia, Autor autor, Editorial editorial, Categoria categoria) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.stock = stock;
        this.precioSugerido = precioSugerido;
        this.ganancia = ganancia;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
    }

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public Libro(String titulo, int stock, double precioSugerido, double ganancia, Autor autor, Editorial editorial, Categoria categoria) {
        this.titulo = titulo;
        this.stock = stock;
        this.precioSugerido = precioSugerido;
        this.ganancia = ganancia;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
    }

    public Libro(int id_libro, String titulo, double precioSugerido, double ganancia, Autor autor, Editorial editorial, Categoria categoria) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.precioSugerido = precioSugerido;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
    }
    
    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(double precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
