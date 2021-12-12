package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
private TextView textPassword;
private TextView textNoAccount;
private Button buttonconnexion;
private TextInputEditText Email;
private TextInputEditText Password;
private FirebaseAuth firebaseAuth;
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
        firebaseAuth=FirebaseAuth.getInstance();
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

        buttonconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
       if (firebaseAuth.getCurrentUser()!=null){
           startActivity(new Intent(login.this,EmailVerifyActivity.class));
           finish();
       }
    }

    public  void setValue(){
        textPassword=findViewById(R.id.password_forgot);
        textNoAccount=findViewById(R.id.no_account);
        buttonconnexion=findViewById(R.id.connexion_button);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);

    }
    private String email,password;

    public void verification() {
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();


        if (TextUtils.isEmpty(email)){
            Email.setError("Email is Required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Invalide email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            Password.setError("Password must be altleast 8 characters ");
            return;
        }
        if (password.length()<8){
            Password.setError("Password must be altleast 8 characters ");
            return;
        }

        Usersignup();
    }
    public void Usersignup(){

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(login.this,EmailVerifyActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

   }
}