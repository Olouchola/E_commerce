package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class login extends AppCompatActivity {
private TextView textPassword;
private TextView textNoAccount;
private Button buttonconnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setValue();
        ImageButton imageButton=(ImageButton)findViewById(R.id.backbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public  void setValue(){
        textPassword=findViewById(R.id.password_forgot);
        textNoAccount=findViewById(R.id.no_account);
        buttonconnexion=findViewById(R.id.connexion_button);

        textPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        textNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}