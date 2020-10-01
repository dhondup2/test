package com.example.hivemanager;

import android.os.AsyncTask;

import com.mysql.jdbc.Blob;


public class CreateAccountAsync extends AsyncTask<Object,Void,Void> {

    @Override
    protected Void doInBackground(Object... objects) {
        SQLConnection connect = new SQLConnection();
        String username = (String) objects[0];
        String password = (String) objects[1];
        Blob picture = (Blob) objects[2];
        String apiary = (String) objects[3];
        String email = (String) objects[4];
        String phone = (String) objects[5];
        connect.createAccount(username, password, null, apiary, email, phone);
        connect.closeConnection();
        return null;
    }
}
