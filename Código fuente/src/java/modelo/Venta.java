package modelo;

import java.io.Serializable;
import java.util.Date;

public class Venta implements Serializable{
    private int id_venta;
    private double totalVenta;
    private Date fechaVenta;
    private String tipoDocumento;
    private Empresa empresa;
    private Cliente cliente;
    private String codigoVenta;

    public Venta(int id_venta, double totalVenta, Date fechaVenta, String tipoDocumento, Empresa empresa, Cliente cliente, String codigoVenta) {
        this.id_venta = id_venta;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
        this.tipoDocumento = tipoDocumento;
        this.empresa = empresa;
        this.cliente = cliente;
        this.codigoVenta = codigoVenta;
    }

    public Venta(Date fechaVenta, String codigoVenta) {
        this.fechaVenta = fechaVenta;
        this.codigoVenta = codigoVenta;
    }

    public Venta(double totalVenta, Date fechaVenta, String tipoDocumento, Empresa empresa, Cliente cliente, String codigoVenta) {
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
        this.tipoDocumento = tipoDocumento;
        this.empresa = empresa;
        this.cliente = cliente;
        this.codigoVenta = codigoVenta;
    }
    
    
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }
}
