package com.example.controlescolar.Model;

public class LoginResponse {
    private int rsp;
    private String token;
    private String username;
    private int typeUser;
    private String nombreCompleto;

    public LoginResponse(int rsp, String token, String username, int typeUser, String nombreCompleto) {
        this.rsp = rsp;
        this.token = token;
        this.username = username;
        this.typeUser = typeUser;
        this.nombreCompleto = nombreCompleto;
    }

    public int getRsp() {
        return rsp;
    }

    public void setRsp(int rsp) {
        this.rsp = rsp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
