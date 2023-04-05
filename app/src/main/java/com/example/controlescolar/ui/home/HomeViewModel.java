package com.example.controlescolar.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.controlescolar.LocalStorage.DbLite;
import com.example.controlescolar.Model.UserInformation;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mName, userName,userType;

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getUserType() {
        return userType;
    }

    private DbLite db;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        this.db = new DbLite(this.context, "",null, 1);
        UserInformation ui = this.db.getInformationCurrentUser();
        mName.setValue(ui.getNombreCompleto());
        userName.setValue(ui.getNombreUsuario());
        userType.setValue(ui.getTipoUsuario());
    }

    private Context context;


    public HomeViewModel() {
        mName = new MutableLiveData<>();
        userType = new MutableLiveData<>();
        userName= new MutableLiveData<>();
    }

    public LiveData<String> getName() {
        return mName;
    }
}