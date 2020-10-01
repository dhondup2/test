package com.example.hivemanager;

import android.os.AsyncTask;

public class DeleteHiveAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        String hive = strings[2];
        connect.deleteHive(username, apiary, hive);
        connect.closeConnection();
        return null;
    }
}