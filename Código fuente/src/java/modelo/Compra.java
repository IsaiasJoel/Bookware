package modelo;

import java.io.Serializable;
import java.util.Date;

public class Compra implements Serializable{
    private int id_compra;
    private double totalCompra;
    private Date fechaCompra;
    private Empresa oEmpresa;
    private Proveedor oProveedor;
    private String codigoCompra;
    
//    NOTA: no necesariamente una compra tiene una lst de detalles de compra
//    creo que se puede vincular un detalleCompra con su objeto compra

    public Compra(double totalCompra, Date fechaCompra, Empresa oEmpresa, Proveedor oProveedor, String codigoCompra) {
        this.totalCompra = totalCompra;
        this.fechaCompra = fechaCompra;
        this.oEmpresa = oEmpresa;
        this.oProveedor = oProveedor;
        this.codigoCompra = codigoCompra;
    }

    public Compra(int id_compra, double totalCompra, Date fechaCompra, Empresa oEmpresa, Proveedor oProveedor, String codigoCompra) {
        this.id_compra = id_compra;
        this.totalCompra = totalCompra;
        this.fechaCompra = fechaCompra;
        this.oEmpresa = oEmpresa;
        this.oProveedor = oProveedor;
        this.codigoCompra = codigoCompra;
    }

    public Compra() {
    }
    
    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Empresa getoEmpresa() {
        return oEmpresa;
    }

    public void setoEmpresa(Empresa oEmpresa) {
        this.oEmpresa = oEmpresa;
    }

    public Proveedor getoProveedor() {
        return oProveedor;
    }

    public void setoProveedor(Proveedor oProveedor) {
        this.oProveedor = oProveedor;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    @Override
    public String toString() {
        return this.id_compra + "-" + codigoCompra;
    }
}
