package com.example.controlescolar.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.controlescolar.Ws.ClassBaseWebService;
import com.example.controlescolar.Ws.Dao;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(new Dao(null).getEntornoApp());
    }

    public LiveData<String> getText() {
        return mText;
    }
}