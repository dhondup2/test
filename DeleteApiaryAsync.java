package com.example.hivemanager;

import android.os.AsyncTask;

public class DeleteApiaryAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        connect.deleteApiary(username, apiary);
        connect.closeConnection();
        return null;
    }
}