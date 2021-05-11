package modelo;

import java.io.Serializable;

public class Proveedor implements Serializable{
    private int id_proveedor;
    private String nombres;
    private String celular;
    private String direccion;

    public Proveedor(int id_proveedor, String nombres, String celular, String direccion) {
        this.id_proveedor = id_proveedor;
        this.nombres = nombres;
        this.celular = celular;
        this.direccion = direccion;
    }

    public Proveedor(String nombres, String celular, String direccion) {
        this.nombres = nombres;
        this.celular = celular;
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    public Proveedor(int id_proveedor, String nombres) {
        this.id_proveedor = id_proveedor;
        this.nombres = nombres;
    }

    public Proveedor(String nombres) {
        this.nombres = nombres;
    }

    public Proveedor() {
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
