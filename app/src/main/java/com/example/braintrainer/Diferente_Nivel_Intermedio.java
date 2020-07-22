package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class Diferente_Nivel_Intermedio extends AppCompatActivity {
    Button iniciar , salir , bt1 , bt2 , bt3 , bt4 ,bt5 ,bt6;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    TextView aciertos;
    MediaPlayer sonido , sonidoperder , sonidoincorrecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diferente__nivel__intermedio);
        iniciar = (Button) findViewById(R.id.IniciarJuegoDiferente);
        salir = (Button) findViewById(R.id.SalirJuegoDiferente);
        chronometer = (Chronometer) findViewById(R.id.CronoDiferente);
        bt1 = (Button)findViewById(R.id.Boton1);
        bt2 = (Button)findViewById(R.id.Boton2);
        bt3 = (Button)findViewById(R.id.Boton3);
        bt4 = (Button)findViewById(R.id.Boton4);
        bt5 = (Button)findViewById(R.id.Boton5);
        bt6 = (Button)findViewById(R.id.Boton6);
        sonido = MediaPlayer.create(this,R.raw.sonidovictoria);
        sonidoperder = MediaPlayer.create(this,R.raw.sonidoperder);
        sonidoincorrecto = MediaPlayer.create(this,R.raw.sonido_respuesta_incorrecta);
        aciertos = (TextView) findViewById(R.id.AciertosDiferente);

        // Botones Bloqueados
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                builder.setTitle(" ¿ Deseas Salir de Figuras Pares ?");
                //builder.setMessage("");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this , NivelesMemorama.class);
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
    public void StartChronometer(){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void PauseChronometer(){
        if(running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void ResetChronometer(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    public void iniciar(){
        StartChronometer();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 20000){
                    PauseChronometer();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                    // Este es el sonido que suena con la perdida
                    sonidoperder.start();
                    //builder.setTitle(" ¿ Deseas Salir de Figuras Pares ?");
                    //builder.setMessage(" Has Ganado !!");
                    builder.setView(R.layout.dialogo_perdedor);
                    builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            iniciar();
                        }
                    });
                    builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        bt1.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
        bt4.setEnabled(true);
        bt5.setEnabled(true);
        bt6.setEnabled(true);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonidoincorrecto.start();
                builder.setView(R.layout.dialogo_perdedor_diferente);
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PauseChronometer();
                        ResetChronometer();
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonidoincorrecto.start();
                builder.setView(R.layout.dialogo_perdedor_diferente);
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PauseChronometer();
                        ResetChronometer();
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonidoincorrecto.start();
                builder.setView(R.layout.dialogo_perdedor_diferente);
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PauseChronometer();
                        ResetChronometer();
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonido.start();
                builder.setView(R.layout.dialogo_ganador);
                aciertos.setText("Puntuación: 5");
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonidoincorrecto.start();
                builder.setView(R.layout.dialogo_perdedor_diferente);
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PauseChronometer();
                        ResetChronometer();
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Diferente_Nivel_Intermedio.this);
                sonidoincorrecto.start();
                builder.setView(R.layout.dialogo_perdedor_diferente);
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PauseChronometer();
                        ResetChronometer();
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Diferente_Nivel_Intermedio.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });



    }

}
