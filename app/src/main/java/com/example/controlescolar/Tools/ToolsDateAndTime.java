package com.example.controlescolar.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToolsDateAndTime {
    public static String GetDateExpirationSession(){

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(new Date().toString()));
            c.add(Calendar.DATE, 15);
            return c.getTime().toString();
        } catch (ParseException e) {
            System.err.println("Error al parsear fecha: "+e.getMessage());
        }
        return "";
    }
}
