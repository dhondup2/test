package com.example.hivemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class CreatePublicProfile extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static boolean  rslt,hlth,hny,qn,hve,invent,lss,gn = false;
    Uri imageUri;
    ImageView imageView;
    Button Upload_Button,Create_Profile;
    CheckBox result,health,honey,queen,hiveequip,inventequip,losses,gains;
    EditText PublicProfileAddress_PlainText, PublicProfilePhone_PlainText, PublicProfileEmail_PlainText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_public_profile);



        PublicProfileAddress_PlainText = (EditText) findViewById(R.id.newAccountAddress_PlainText);
        PublicProfilePhone_PlainText = (EditText) findViewById(R.id.newAccountPhone_PlainText);
        PublicProfileEmail_PlainText = (EditText) findViewById(R.id.newAccountEmail_PlainText);
        imageView = (ImageView) findViewById(R.id.IDProf);
        imageView.setImageResource(R.drawable.bee1);
        Upload_Button = (Button) findViewById(R.id.upload_Button);
        Create_Profile = (Button) findViewById(R.id.CreatePublicProfile_Button) ;
        Create_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
        Upload_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        result = findViewById(R.id.result);
        health = findViewById(R.id.health);
        queen = findViewById(R.id.queen);
        honey = findViewById(R.id.honey);
        hiveequip = findViewById(R.id.equipmentHive);
        inventequip = findViewById(R.id.equipmentInvent);
        losses = findViewById(R.id.loss);
        gains = findViewById(R.id.gain);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.isChecked()){
                    rslt = true;
                }
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(health.isChecked()){
                    hlth = true;
                }
            }
        });
        queen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(queen.isChecked()){
                    qn = true;
                }
            }
        });
        honey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(honey.isChecked()){
                    hny = true;
                }
            }
        });
        hiveequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hiveequip.isChecked()){
                    hve = true;
                }
            }
        });
        inventequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inventequip.isChecked()){
                    invent = true;
                }
            }
        });
        losses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(losses.isChecked()){
                    lss = true;
                }
            }
        });
        gains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gains.isChecked()){
                    gn = true;
                }
            }
        });
    }

    private void sendData() {

        AlertDialog.Builder b = new AlertDialog.Builder(CreatePublicProfile.this);
        //Somehow store one photo and send it
        String phone = PublicProfilePhone_PlainText.getText().toString();
        String email = PublicProfileEmail_PlainText.getText().toString();
        String adr = PublicProfileAddress_PlainText.getText().toString();

        //use bool values and pass to backend
        if(!( adr.equals("") || phone.equals("") || email.equals("") ||email.equals("Email Address") || adr.equals("Apiary Address") || phone.equals("Phone Number") )){
            //Display Error
            // somehow send image as well??
            // then pass data to backend
            //send data to backend and go back to login page

        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }


}