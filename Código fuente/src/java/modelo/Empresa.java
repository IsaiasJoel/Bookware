package modelo;

import java.io.Serializable;

public class Empresa implements Serializable {
    private int id_empresa;
    private String razonSocial;
    private String RUC;
    private String logo;

    public Empresa() {
    }

    public Empresa(int id_empresa, String razonSocial, String RUC, String logo) {
        this.id_empresa = id_empresa;
        this.razonSocial = razonSocial;
        this.RUC = RUC;
        this.logo = logo;
    }

    public Empresa(String razonSocial, String RUC, String logo) {
        this.razonSocial = razonSocial;
        this.RUC = RUC;
        this.logo = logo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return this.id_empresa + " - " + this.RUC + " - "+ this.logo;
    }
}
