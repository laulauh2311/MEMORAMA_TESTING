package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NivelesFiguraDiferente extends AppCompatActivity {
    private Button facil, intermedio , dificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_figura_diferente);
        facil = (Button) findViewById(R.id.NivelFacilDiferente);
        intermedio = (Button)findViewById(R.id.NivelIntermedioDiferente);
        dificil = (Button) findViewById(R.id.NivelDificilDiferente);

        facil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesFiguraDiferente.this ,DiferenteNivelFacil.class);
                startActivity(i);
            }
        });

        intermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesFiguraDiferente.this ,Diferente_Nivel_Intermedio.class);
                startActivity(i);
            }
        });
    }
}