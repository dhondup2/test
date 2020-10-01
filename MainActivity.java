package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button Login_Button, CreateNewAccount_Button;
    EditText LogicUsername_PlainText,LoginPassword_PlainText;
    //ImageView uploadPicture_ImageView;

    public static final int GET_FROM_GALLERY = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Button = (Button) findViewById(R.id.Login_Button);
        CreateNewAccount_Button = (Button) findViewById(R.id.CreateNewAccount_Button);





        LogicUsername_PlainText = (EditText) findViewById(R.id.LogicUsername_PlainText);
        LoginPassword_PlainText = (EditText) findViewById(R.id.LoginPassword_PlainText);

        //uploadPicture_ImageView = (ImageView) findViewById(R.id.IDProf);

        Login_Button.setOnClickListener(this);
        CreateNewAccount_Button.setOnClickListener(this);

    }
    // This needs to be called after pressing
    private void sendData() {
        String username = LogicUsername_PlainText.getText().toString();
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        String pass = LoginPassword_PlainText.getText().toString();
        if(!(username.equals("Username") || pass.equals("1"))){
            //Send data to back end with photo
            new SearchAccountAsync().execute(username, pass);

        }
    }







    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Login_Button:
                Intent intent2MainActivity2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent2MainActivity2);
                break;
            case R.id.CreateNewAccount_Button:
                Intent intent2CreateNewAccountPage = new Intent(MainActivity.this, CreateNewAccount.class);
                startActivity(intent2CreateNewAccountPage);
                break;
        }
    }
}