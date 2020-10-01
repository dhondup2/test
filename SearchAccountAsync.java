package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.Blob;

public class SearchAccountAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String password = strings[1];
        connect.searchAccount(username, password);
        connect.closeConnection();
        return null;
    }
}
