package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/////////////////////////////////////////////////
//                                             //
//   Programadores de Codigo:                  //
//   - Alvaro Berrios Zuniga                   //
//   - Christian Yataco Tapia                  //
//                                             //
//     Testers:                                //
//   - Andrea Laura Oliva                      //
//   - Manuel Hernandez Medina                 //
//                                             //
//    Usuario:                                 //
//   - Manuel Hernandez Medina                 //
//                                             //
/////////////////////////////////////////////////


public class MainActivity extends AppCompatActivity {
    private ImageView imagen;
    private static int splashTimeOut=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView)findViewById(R.id.brain);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        imagen.startAnimation(anim);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(i);
            }
        },splashTimeOut);
    }
}