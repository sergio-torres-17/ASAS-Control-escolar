package com.example.controlescolar.Ws;

import android.content.Context;

import com.android.volley.Response;
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
}
