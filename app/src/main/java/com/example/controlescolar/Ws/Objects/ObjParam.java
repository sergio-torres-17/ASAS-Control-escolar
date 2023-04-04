package com.example.controlescolar.Ws.Objects;

public class ObjParam {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String key;
    private String value;
    public ObjParam(String key, String value) {
        this.key = key;
        this.value = value;
    }



}
