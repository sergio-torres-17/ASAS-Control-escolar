package com.example.controlescolar.LocalStorage;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.controlescolar.Model.LoginResponse;

public class SessionControl {
    private Context context;
    private SharedPreferences preferences;

    public SessionControl(Context context) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
    }
    public void saveSession(LoginResponse response){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("token", response.getToken());
        System.out.println("Guardando token "+response.getToken());
    }
    public String GetTokenSession(){
        return this.preferences.getString("token", "");
    }
}
