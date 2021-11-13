package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private TextView textAccount;
    private TextInputEditText fullname;
    private TextInputEditText emailText;
    private TextInputEditText passwordText;
    private TextInputEditText passwordconfirme;
    private ProgressDialog progressDialog;
    private Button connexion;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textAccount=findViewById(R.id.account);
        connexion=findViewById(R.id.connexion_button);
        getInfoId();
        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        ImageButton imageButton=(ImageButton)findViewById(R.id.backbtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitData();
            }
        });
    }
   public  void getInfoId(){
       fullname=findViewById(R.id.full_name);
       emailText=findViewById(R.id.email);
       passwordText=findViewById(R.id.password);
       passwordconfirme=findViewById(R.id.confirme_password);
    }
    private String Fullname,email,password,confim_password;
    private void InitData() {
         Fullname=fullname.getText().toString().trim();
         email=emailText.getText().toString().trim();
         password=passwordText.getText().toString().trim();
         confim_password=passwordconfirme.getText().toString().toString();

        if (TextUtils.isEmpty(Fullname)){
            fullname.setError("Full name is required");
            return;
        }
        if (TextUtils.isEmpty(email)){
            emailText.setError("Email is required");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Invalide email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            passwordText.setError("Password must be altleast 8 characters ");
            return;
        }
        if (password.length()<8){
            passwordText.setError("Password must be altleast 8 characters ");
            return;
        }
        if (!password.equals(confim_password)){
            passwordconfirme.setError("Password doesn't match");
            return;
        }
        CreateAccount();
    }

    private void CreateAccount() {
        progressDialog.setMessage("Creating Account ..");
        progressDialog.show();
    //create account users in firebase

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                //send email verification
                FirebaseUser fuser=firebaseAuth.getCurrentUser();
                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Verification email has been send", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Email not send"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(RegisterActivity.this, "User create successful", Toast.LENGTH_SHORT).show();
                userID=firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference=fstore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("FulName",""+Fullname);
                user.put("Email",""+email);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "user profil created successul", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(RegisterActivity.this,DashbordActivity.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}