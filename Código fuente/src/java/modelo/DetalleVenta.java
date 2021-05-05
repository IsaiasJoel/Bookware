package modelo;

import java.io.Serializable;

public class DetalleVenta implements Serializable {
    private int id_detalleVenta;
    private double subtotal;
    private double precioVenta;
    private int cantidad;
    private Venta venta;
    private Libro libro;

    public DetalleVenta(double subtotal, double precioVenta, int cantidad, Libro libro) {
        this.subtotal = subtotal;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.libro = libro;
    }

    public DetalleVenta(int id_detalleVenta, double subtotal, double precioVenta, int cantidad, Venta venta, Libro libro) {
        this.id_detalleVenta = id_detalleVenta;
        this.subtotal = subtotal;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.venta = venta;
        this.libro = libro;
    }
    
    public int getId_detalleVenta() {
        return id_detalleVenta;
    }

    public void setId_detalleVenta(int id_detalleVenta) {
        this.id_detalleVenta = id_detalleVenta;
    }

    public double getSubtotal() {
        return this.cantidad * this.precioVenta;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
