package modelo;

import java.io.Serializable;

public class DetalleCompra implements Serializable {
    private int id_detalleCompra;
    private double subtotal;
    private double precioCompra;
    private int cantidad;
    private Compra oCompra;
    private Libro oLibro;

    public DetalleCompra(int id_detalleCompra, double subtotal, double precioCompra, int cantidad, Compra oCompra, Libro oLibro) {
        this.id_detalleCompra = id_detalleCompra;
        this.subtotal = subtotal;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.oCompra = oCompra;
        this.oLibro = oLibro;
    }

    public DetalleCompra() {
    }

    public DetalleCompra(double subtotal, double precioCompra, int cantidad, Compra oCompra, Libro oLibro) {
        this.subtotal = subtotal;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.oCompra = oCompra;
        this.oLibro = oLibro;
    }

    public DetalleCompra(double subtotal, double precioCompra, int cantidad, Libro oLibro) {
        this.subtotal = subtotal;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.oLibro = oLibro;
    }
    
    
    

    public int getId_detalleCompra() {
        return id_detalleCompra;
    }

    public void setId_detalleCompra(int id_detalleCompra) {
        this.id_detalleCompra = id_detalleCompra;
    }

    public double getSubtotal() {
        return this.cantidad*this.precioCompra;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getoCompra() {
        return oCompra;
    }

    public void setoCompra(Compra oCompra) {
        this.oCompra = oCompra;
    }

    public Libro getoLibro() {
        return oLibro;
    }

    public void setoLibro(Libro oLibro) {
        this.oLibro = oLibro;
    }
}
