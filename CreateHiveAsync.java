package com.example.hivemanager;

import android.os.AsyncTask;

public class CreateHiveAsync extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        SQLConnection connect = new SQLConnection();
        String username = strings[0];
        String apiary = strings[1];
        String hive = strings[2];
        String inspection = strings[3];
        String health = strings[4];
        String honey = strings[5];
        String queenproduction = strings[6];
        String equiphive = strings[7];
        String equipinven = strings[8];
        int loss = Integer.parseInt(strings[9]);
        int gain = Integer.parseInt(strings[10]);
        connect.createHive(username, apiary, hive, inspection, health, honey, queenproduction, equiphive, equipinven,
                loss, gain);
        connect.closeConnection();
        return null;
    }
}