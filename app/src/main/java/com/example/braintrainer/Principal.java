package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {
    private Button memorama , figurasdiferentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        memorama = (Button) findViewById(R.id.Memorama);
        figurasdiferentes = (Button)findViewById(R.id.FigurasDiferentes);

        memorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this , NivelesMemorama.class);
                startActivity(i);
            }
        });

        figurasdiferentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this , NivelesFiguraDiferente.class);
                startActivity(i);
            }
        });

    }
}