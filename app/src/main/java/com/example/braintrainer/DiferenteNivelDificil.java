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

public class DiferenteNivelDificil extends AppCompatActivity {
    Button iniciar , salir , bt1 , bt2 , bt3 , bt4 ,bt5 ,bt6,bt7,bt8,bt9;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    TextView aciertos;
    MediaPlayer sonido , sonidoperder , sonidoincorrecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diferente_nivel_dificil);
        iniciar = (Button) findViewById(R.id.IniciarJuegoDiferente);
        salir = (Button) findViewById(R.id.SalirJuegoDiferente);
        chronometer = (Chronometer) findViewById(R.id.CronoDiferente);
        sonido = MediaPlayer.create(this,R.raw.sonidovictoria);
        sonidoperder = MediaPlayer.create(this,R.raw.sonidoperder);
        sonidoincorrecto = MediaPlayer.create(this,R.raw.sonido_respuesta_incorrecta);
        aciertos = (TextView) findViewById(R.id.AciertosDiferente);

        // BOTONES
        bt1 = (Button)findViewById(R.id.Boton1);
        bt2 = (Button)findViewById(R.id.Boton2);
        bt3 = (Button)findViewById(R.id.Boton3);
        bt4 = (Button)findViewById(R.id.Boton4);
        bt5 = (Button)findViewById(R.id.Boton5);
        bt6 = (Button)findViewById(R.id.Boton6);
        bt7 = (Button)findViewById(R.id.Boton7);
        bt7 = (Button)findViewById(R.id.Boton8);
        bt9 = (Button)findViewById(R.id.Boton9);

        // Botones Bloqueados
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
        bt5.setEnabled(false);
        bt6.setEnabled(false);
        bt7.setEnabled(false);
        bt8.setEnabled(false);
        bt9.setEnabled(false);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
                builder.setTitle(" ¿ Deseas Salir de Figuras Pares ?");
                //builder.setMessage("");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(DiferenteNivelDificil.this , NivelesMemorama.class);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                            Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
        bt7.setEnabled(true);
        bt8.setEnabled(true);
        bt9.setEnabled(true);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
                sonido.start();
                builder.setView(R.layout.dialogo_ganador);
                aciertos.setText("Puntuación: 10");
                builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iniciar();
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
                        startActivity(i);
                        finish();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiferenteNivelDificil.this);
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
                        Intent i = new Intent(DiferenteNivelDificil.this ,NivelesFiguraDiferente.class);
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