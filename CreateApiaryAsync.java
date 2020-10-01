package com.example.hivemanager;

import android.os.AsyncTask;

public class CreateApiaryAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        connect.createApiary(username, apiary);
        connect.closeConnection();
        return null;
    }
}