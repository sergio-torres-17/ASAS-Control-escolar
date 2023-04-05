package com.example.controlescolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.controlescolar.LocalStorage.DbLite;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (new DbLite(this,"",null,1).isLogged()){
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }

    }
}