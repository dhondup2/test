package com.example.hivemanager;

import android.os.AsyncTask;

public class UpdateApiaryAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String oldpiary = strings[1];
        String newpiary = strings[2];
        connect.updateApiary(username, oldpiary, newpiary);
        connect.closeConnection();
        return null;
    }
}