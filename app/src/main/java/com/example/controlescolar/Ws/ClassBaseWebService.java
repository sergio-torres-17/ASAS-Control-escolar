package com.example.controlescolar.Ws;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.LocalStorage.SessionControl;
import com.example.controlescolar.Ws.Objects.ObjParam;
import java.util.HashMap;
import java.util.Map;

public abstract class ClassBaseWebService {
    private static final String URL_BASE_WEB_SERVICES_DEV_LOCAL = "https://localhost:7297/api/";
    private static final String URL_BASE_WEB_SERVICES_PRD_LOCAL = "http://192.168.0.173:8024/api/";
    private static final String URL_BASE_WEB_SERVICES_PRD_GLOBAL = "http://mrsergiotorres17-001-site1.itempurl.com/api/";
    private static final String URL_BASE_WEB_SERVICES = URL_BASE_WEB_SERVICES_PRD_GLOBAL;
    public void GetRequest(String url, Response.Listener<String> eventResponse, ObjParam[] parameters, Context context){
        StringRequest sr = new StringRequest(Request.Method.GET, URL_BASE_WEB_SERVICES+url, eventResponse, error -> System.err.println("Ha ocurrido un error al hacer la petición: "+error.getMessage())){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (parameters !=null){
                    Map<String, String> params = new HashMap<String, String>();
                    for (ObjParam p: parameters)
                        params.put(p.getKey(), p.getValue());
                    return params;
                }else
                    return super.getParams();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.getCache().clear();
        queue.add(sr);
    }
    public void PostRequest(String url, Response.Listener<String> eventResponse, ObjParam[] parameters, Context context){
        StringRequest sr = new StringRequest(Request.Method.POST, URL_BASE_WEB_SERVICES+url, eventResponse, error -> System.err.println("Ha ocurrido un error al hacer la petición: "+error.getMessage())){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (parameters !=null){
                    Map<String, String> params = new HashMap<String, String>();
                    for (ObjParam p: parameters){
                        params.put(p.getKey(), p.getValue());
                        System.out.println(p.getKey()+":"+p.getValue());
                    }
                    return params;
                }else
                    return super.getParams();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.getCache().clear();
        queue.add(sr);
    }
    public void PostRequest(String url, Response.Listener<String> eventResponse, ObjParam[] parameters, boolean needAuth,Context context){
        StringRequest sr = new StringRequest(Request.Method.POST, URL_BASE_WEB_SERVICES+url, eventResponse, error -> System.err.println("Ha ocurrido un error al hacer la petición: "+error.getMessage())){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (parameters !=null){
                    Map<String, String> params = new HashMap<String, String>();
                    for (ObjParam p: parameters)
                        params.put(p.getKey(), p.getValue());
                    return params;
                }else
                    return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (needAuth){
                    Map<String,String> headers = new HashMap<>();
                    System.out.println("Token "+new DbLite(context, "", null, 1).getInformationCurrentUser().getToken());
                    headers.put("Authorization", "Bearer "+new DbLite(context, "", null, 1).getInformationCurrentUser().getToken());
                    return headers;
                }else {
                    return super.getHeaders();
                }

            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.getCache().clear();
        queue.add(sr);
    }
    public String getEntornoApp(){
        switch (URL_BASE_WEB_SERVICES){
            case URL_BASE_WEB_SERVICES_DEV_LOCAL:{
                return "Versión 1.0 Entorno de pruebas Local ";
            }
            case URL_BASE_WEB_SERVICES_PRD_GLOBAL:{
                return "Versión 1.0 Entorno de producción ";
            }
            case URL_BASE_WEB_SERVICES_PRD_LOCAL:{
                return "Versión 1.0 Entorno de pruebas Local secundario";
            }
            default:{
                return "Not Environment Detected!";
            }
        }
    }

}
