package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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


public class VerPuntaje extends AppCompatActivity {
    TableRow puntaje,puntajeintermedio,total;
    TextView facil;
    Button guardar,salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_puntaje);
        puntaje = (TableRow) findViewById(R.id.PuntajeFacil);
        puntajeintermedio = (TableRow) findViewById(R.id.PuntajeIntermedio);
        total = (TableRow)findViewById(R.id.PuntajeTotal);
        total.setVisibility(View.VISIBLE);
        puntajeintermedio.setVisibility(View.VISIBLE);
        puntaje.setVisibility(View.VISIBLE);
        facil = (TextView) findViewById(R.id.Facil);
        guardar = (Button) findViewById(R.id.Guardar);
        salir = (Button) findViewById(R.id.Salir);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerPuntaje.this,"Puntajes Guardados",Toast.LENGTH_SHORT).show();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPuntaje.this);
                builder.setTitle(" ¿ Deseas Salir de Figuras Pares ?");
                //builder.setMessage("");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(VerPuntaje.this , NivelesMemorama.class);
                        startActivity(i);
                        finish();
                    }
                });
                builder.setNegativeButton("No",null);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}