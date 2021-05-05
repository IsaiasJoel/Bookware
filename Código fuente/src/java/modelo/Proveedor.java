package modelo;

import java.io.Serializable;

public class Proveedor implements Serializable{
    private int id_proveedor;
    private String nombres;

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
