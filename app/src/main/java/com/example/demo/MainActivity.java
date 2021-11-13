package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
 private Button Inscription;
 private Button Connexion;
 private ImageView imageAnimate;

 float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inscription=findViewById(R.id.inscription);
        Connexion=findViewById(R.id.connexion);
        imageAnimate=findViewById(R.id.image_textview);
//        RotateAnimation rote=new RotateAnimation(2000,3000);
//        rote.setDuration(50000);
//        TranslateAnimation trans=new TranslateAnimation(0,100,0,100);
//        trans.setDuration(500);
//        ScaleAnimation scale = new ScaleAnimation(0, 2, 0, 2);
//        scale.setDuration(500);
//        AlphaAnimation alpha = new AlphaAnimation(0, 100);
//        alpha.setDuration(500);
        imageAnimate.setTranslationX(800);
        Inscription.setTranslationX(800);
        Connexion.setTranslationX(800);

        imageAnimate.setAlpha(v);
        Inscription.setAlpha(v);
        Connexion.setAlpha(v);

        imageAnimate.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Inscription.animate().translationX(-1).alpha(1).setDuration(800).setStartDelay(300).start();
        Connexion.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
       Inscription.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
               startActivity(intent);
           }
       });

    }
}