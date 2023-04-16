package com.example.controlescolar.Ws;

import android.content.Context;

import com.android.volley.Response;
import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.Ws.Objects.ObjParam;

public class Dao extends ClassBaseWebService {
    private Context context;

    public Dao(Context context) {
        this.context = context;
    }

    public void login(Response.Listener<String> listener, String user, String password){
        PostRequest("Login",listener,
                new ObjParam[]{
                        new ObjParam("Username", user),
                        new ObjParam("Password", password)
        },
                this.context);
    }
    public void insertarAsistencia(Response.Listener<String> listener, String codigoClase){
        PostRequest("InsertarAsistencia", listener,
                new ObjParam[]{
                        new ObjParam("CodigoClase", codigoClase),
                        new ObjParam("NombreAlumno", new DbLite(this.context, "",null, 1).getInformationCurrentUser().getNombreCompleto())
                },true ,this.context
                );
    }
    public void insertarAsistenciaProfesor(Response.Listener<String> listener, String codigoClase){
        PostRequest("InsertarAsistenciaProfesor", listener,
                new ObjParam[]{
                        new ObjParam("CodigoClase", codigoClase),
                        new ObjParam("NombreProfesor", new DbLite(this.context, "",null, 1).getInformationCurrentUser().getNombreCompleto())
                },true ,this.context
        );
    }
}
