package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class EmailVerifyActivity extends AppCompatActivity {
  private TextView verify;
  private Button buttonVerify;
  private Button buttondecoonexion;
  private FirebaseAuth auth;
  private FirebaseUser user;
  private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);
        verify=findViewById(R.id.email_not_verify);
        buttonVerify=findViewById(R.id.verify_now);
        buttondecoonexion=findViewById(R.id.deconnexion);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,new HomeFragment()).commit();

        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        if (!user.isEmailVerified()){
            verify.setVisibility(View.VISIBLE);
            buttonVerify.setVisibility(View.VISIBLE);
            buttonVerify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Verification email has been send", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: Email not send "+e.getMessage());
                        }
                    });
                }
            });

        }else {
            buttonVerify.setVisibility(View.GONE);
            buttonVerify.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Email verify", Toast.LENGTH_SHORT).show();
        }

     buttondecoonexion.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             FirebaseAuth.getInstance().signOut();
             startActivity(new Intent(EmailVerifyActivity.this,login.class));
         }
     });
    }
}