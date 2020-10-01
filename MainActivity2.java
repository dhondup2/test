package com.example.hivemanager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button ManageApiaries_Button, ManageHive_Button, CreatePublicProfile_Button, Logout_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ManageApiaries_Button = (Button) findViewById(R.id.ManageApiaries_Button);
        ManageHive_Button = (Button) findViewById(R.id.ManageHive_Button);
        CreatePublicProfile_Button = (Button) findViewById(R.id.CreatePublicProfile_Button);
        Logout_Button = (Button) findViewById(R.id.Logout_Button);

        Logout_Button.setOnClickListener(this);
        ManageHive_Button.setOnClickListener(this);
        CreatePublicProfile_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Logout_Button:
                Intent intent2MainActivity = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent2MainActivity);
                break;

            case R.id.ManageHive_Button:
                Intent intent2ManageHiveActivity = new Intent(MainActivity2.this, Hive.class);
                startActivity(intent2ManageHiveActivity);
                break;
            case R.id.CreatePublicProfile_Button:
                Intent intent2CreatePublicProfile = new Intent(MainActivity2.this, CreatePublicProfile.class);
                startActivity(intent2CreatePublicProfile);

                break;

        }
    }
}