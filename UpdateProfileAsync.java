package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.Blob;

public class UpdateProfileAsync extends AsyncTask<Object,Void,Void> {

    @Override
    protected Void doInBackground(Object... objects) {
        SQLConnection connect = new SQLConnection();
        String username = (String) objects[0];
        Blob picture = (Blob) objects[1];
        String email = (String) objects[2];
        String phone = (String) objects[3];
        connect.updateProfile(username, picture, email, phone);
        connect.closeConnection();
        return null;
    }
}
