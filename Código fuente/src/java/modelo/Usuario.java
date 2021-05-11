package modelo;

import java.io.Serializable;

public class Usuario {
    private int id_usuario;
    private String correo;
    private String contraseña;
    private int id_empresa;
    private int id_perfil;

    public Usuario() {
    }

    public Usuario(int id_usuario, String correo, String contraseña, int id_empresa, int id_perfil) {
        this.id_usuario = id_usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.id_empresa = id_empresa;
        this.id_perfil = id_perfil;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
}
