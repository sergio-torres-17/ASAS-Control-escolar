package com.example.controlescolar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.Ws.Dao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.controlescolar.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isProfesor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isProfesor =  (new DbLite(MainActivity.this, "",null, 1).getInformationCurrentUser().getTipoUsuario().equals("Profesor"));
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null)
                Toast.makeText(this, "Cencelado", Toast.LENGTH_LONG).show();
            else {
                if (!isProfesor)
                    new Dao(MainActivity.this).insertarAsistencia(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (response.length() > 0){
                                Toast.makeText(MainActivity.this, obj.getString("msg"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }, result.getContents());
                else
                    new Dao(MainActivity.this).insertarAsistenciaProfesor(new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (response.length() > 0){
                                    Toast.makeText(MainActivity.this, obj.getString("msg"), Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }, result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}