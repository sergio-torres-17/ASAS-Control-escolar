package com.example.controlescolar.LocalStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.controlescolar.Model.LoginResponse;
import com.example.controlescolar.Model.UserInformation;
import com.example.controlescolar.Tools.ToolsDateAndTime;

public class DbLite extends SQLiteOpenHelper {
    public DbLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "DbLocal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Queries.TABLA_INFO_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void RegistrarInfoUsuario(LoginResponse response){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv;
        cv = new ContentValues();
        cv.put("NombreCompleto", response.getNombreCompleto());
        cv.put("NombreUsuario", response.getUsername());
        cv.put("Token", response.getToken());
        cv.put("UserType", (response.getTypeUser()==1150)?"Estudiante": "Profesor");
        cv.put("DateExpiration", ToolsDateAndTime.GetDateExpirationSession());
        db.insert("INFO_USER", null,cv);
    }
    public boolean isLogged(){
        boolean dev;
        dev = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("INFO_USER",new String[]{"NombreCompleto"}, null,null,null,null,null);
        dev = c.getCount()>0;
        c.close();
        return dev;
    }
    public UserInformation getInformationCurrentUser(){
        UserInformation dev = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("INFO_USER",new String[]{"NombreCompleto","NombreUsuario","Token","UserType","DateExpiration"}, null,null,null,null,null);
        if (c.moveToFirst()){
            dev = new UserInformation(
                    c.getString(0)
                    ,c.getString(1)
                    ,c.getString(3)
                    ,c.getString(2)
            );
            System.out.println("Token extraido de base de datos: "+c.getString(2));

        }
        c.close();
        return dev;
    }
}
