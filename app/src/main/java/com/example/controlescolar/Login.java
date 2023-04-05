package com.example.controlescolar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.controlescolar.Crypt.EncryptoAes;
import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.LocalStorage.SessionControl;
import com.example.controlescolar.Model.LoginResponse;
import com.example.controlescolar.Ws.Dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText edtUser, edtPassword;
    private Button btnLogin;
    private EncryptoAes crypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        crypt = new EncryptoAes();
        initializeEvents();
    }
    private void initializeEvents(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println( "Contraseña "+ crypt.encriptar(edtPassword.getText().toString()));
                if (!edtUser.getText().toString().equals("")&&!edtPassword.getText().toString().equals("")){
                    new Dao(Login.this).login(new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            try {
                                JSONObject object = new JSONObject(response);
                                LoginResponse lr = new LoginResponse(
                                        object.getInt("rsp"),
                                        object.getString("token"),
                                        object.getString("username"),
                                        object.getInt("userType"),
                                        object.getString("nombreCompleto")
                                );
                                if (object.getInt("rsp") == 0){
                                    new SessionControl(Login.this).saveSession(lr);
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    new DbLite(Login.this, null, null, 1).RegistrarInfoUsuario(lr);
                                    finish();
                                }else{
                                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                System.err.println( "Error al extraer info "+e.getMessage());
                            }
                        }
                    }, edtUser.getText().toString(), crypt.encriptar(edtPassword.getText().toString()));
                }else
                    Toast.makeText(Login.this, "Se deben de ingresar usuario y contraseña", Toast.LENGTH_LONG).show();
            }
        });
    }
}