package com.example.hivemanager;

import android.os.AsyncTask;

import java.sql.ResultSet;

public class DisplayProfileAsync extends AsyncTask<String, Void, ResultSet> {

    @Override
    protected ResultSet doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        ResultSet rs =connect.displayProfile(username);
        connect.closeConnection();
        return rs;
    }


}