package com.example.controlescolar.Ws;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.controlescolar.LocalStorage.SessionControl;
import com.example.controlescolar.Ws.Objects.ObjParam;
import java.util.HashMap;
import java.util.Map;

public abstract class ClassBaseWebService {
    private static final String URL_BASE_WEB_SERVICES = "http://192.168.0.173:8024/api/";
    public void GetRequest(String url, Response.Listener<String> eventResponse, ObjParam[] parameters, Context context){
        System.out.println("Url completa "+URL_BASE_WEB_SERVICES+url);
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
                    headers.put("Authorization", "Bearer "+new SessionControl(context).GetTokenSession());
                }
                return super.getHeaders();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.getCache().clear();
        queue.add(sr);
    }
}
