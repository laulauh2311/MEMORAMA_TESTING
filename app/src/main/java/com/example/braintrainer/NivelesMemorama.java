package com.example.braintrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


public class NivelesMemorama extends AppCompatActivity {
    private Button facil , intermedio , dificil , puntajes ;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    public TextView bienvenida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles_memorama);
        facil = (Button)findViewById(R.id.NivelFacil);
        intermedio = (Button) findViewById(R.id.NivelIntermedio);
        dificil = (Button) findViewById(R.id.NivelDificil);
        puntajes = (Button) findViewById(R.id.BotonPuntajes);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        bienvenida = (TextView) findViewById(R.id.Usuario);

        facil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesMemorama.this , MemoNivelFacil.class);
                startActivity(i);
            }
        });

        intermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesMemorama.this , Memo_Nivel_Intermedio.class);
                startActivity(i);
            }
        });

        dificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesMemorama.this , MemoNivelDificil.class);
                startActivity(i);
            }
        });

        puntajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NivelesMemorama.this , VerPuntaje.class);
                startActivity(i);
            }
        });
        getUserInfo();

    }
    private void getUserInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mReference.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String Nombre = dataSnapshot.child("Nombres").getValue().toString();
                    //Toast.makeText(NivelesMemorama.this,"Bienvenido(a)" + Nombre,Toast.LENGTH_SHORT).show();
                    bienvenida.setText(Nombre);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}