package com.example.controlescolar.Model;

public class UserInformation {
    public UserInformation(String nombreCompleto, String tipoUsuario, String nombreUsuario) {
        NombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    private String NombreCompleto;
    private String tipoUsuario;
    private String nombreUsuario;


    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
