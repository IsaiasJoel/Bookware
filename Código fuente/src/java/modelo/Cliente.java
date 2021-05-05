package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id_cliente;
    private String dni;
    private String nombres;

    public Cliente() {
    }

    public Cliente(int id_cliente, String dni, String nombres) {
        this.id_cliente = id_cliente;
        this.dni = dni;
        this.nombres = nombres;
    }

    public Cliente(String dni, String nombres) {
        this.dni = dni;
        this.nombres = nombres;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return this.dni + this.nombres;
    }
}
