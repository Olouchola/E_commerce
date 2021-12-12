package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity {
private TextView textViewLogin;
private TextInputEditText Email;
private Button authenticate;
private FirebaseAuth auth;
private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ImageButton imageButton=(ImageButton)findViewById(R.id.backbtn);
        textViewLogin=findViewById(R.id.login_now);
        Email=findViewById(R.id.email);
        authenticate=findViewById(R.id.authenticate_button);
        auth=FirebaseAuth.getInstance();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPasswordActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword();
            }
        });

    }

    private String email;

    private void ResetPassword(){
        email=Email.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Email.setError("Email is required");
            return;
        }
       ForgotPassword();
    }

    private void ForgotPassword() {
        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Reset Link to your email", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error ! Reset link is not send"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}