package modelo;

import java.io.Serializable;

public class Empresa implements Serializable {
    private int id_empresa;
    private String RUC;
    private String usuario;
    private String password;
    private String logo;

    public Empresa() {
    }
    
    

    public Empresa(String RUC, String usuario, String logo) {
        this.RUC = RUC;
        this.usuario = usuario;
        this.logo = logo;
    }
    
    public Empresa(String RUC, String usuario, String password, String logo) {
        this.RUC = RUC;
        this.usuario = usuario;
        this.password = password;
        this.logo = logo;
    }
    
    public Empresa(int id_empresa, String RUC, String usuario, String password, String logo) {
        this.id_empresa = id_empresa;
        this.RUC = RUC;
        this.usuario = usuario;
        this.password = password;
        this.logo = logo;
    }

    public Empresa(int id_empresa, String RUC, String usuario, String logo) {
        this.id_empresa = id_empresa;
        this.RUC = RUC;
        this.usuario = usuario;
        this.logo = logo;
    }

    public Empresa(int id_empresa, String RUC, String usuario) {
        this.id_empresa = id_empresa;
        this.RUC = RUC;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return this.id_empresa + " - " + this.RUC + " - " + this.usuario + " - "+ this.logo;
    }
    
    
    
    
    
}
