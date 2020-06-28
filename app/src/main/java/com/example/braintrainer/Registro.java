package com.example.braintrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText Nombre , Apellido , Edad , Correo , Contraseña;
    private Button registrar;
    private String Nombres="";
    private String Apellidos="";
    private String CorreoElectronico ="";
    private String Edades ="";
    private String ContraseñadeUsuario ="";
    FirebaseAuth mAuth;
    DatabaseReference mDataReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Nombre = (EditText) findViewById(R.id.Nombre);
        Apellido = (EditText) findViewById(R.id.Apellidos);
        Edad = (EditText) findViewById(R.id.Edad);
        Correo = (EditText) findViewById(R.id.Correo);
        Contraseña = (EditText) findViewById(R.id.Contraseña);
        registrar = (Button) findViewById(R.id.Registrar);
        mAuth = FirebaseAuth.getInstance();
        mDataReference = FirebaseDatabase.getInstance().getReference();
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombres = Nombre.getText().toString();
                Apellidos = Apellido.getText().toString();
                Edades = Edad.getText().toString();
                CorreoElectronico = Correo.getText().toString();
                ContraseñadeUsuario = Contraseña.getText().toString();
                if(!Nombres.isEmpty() && !Apellidos.isEmpty() && !CorreoElectronico.isEmpty() && !Edades.isEmpty() && !ContraseñadeUsuario.isEmpty()){
                    if(ContraseñadeUsuario.length() >= 6 && Edades.length() <= 2){
                        registerUser();
                        Toast.makeText(Registro.this,"Usuario Registrado",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registro.this , LoginActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Registro.this,"La contraseña debe tener mas de 6 caracteres o verifique su edad",Toast.LENGTH_SHORT).show();
                    }
                    /*if( Correo == Correo1 && Contraseña == Contraseña1 && Contraseña.length() >= 6 && Contraseña1.length() >= 6){
                        registerUser(); mAuth = FirebaseAuth.getInstance();
        mDataReference = FirebaseDatabase.getInstance().getReference();
                    }else{
                        Toast.makeText(RegistrodeClientes.this,"El Correo o la Contraseña No Son Iguales",Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistrodeClientes.this,"La Contraseña Debe Tener Mas de 6 Caracteres",Toast.LENGTH_SHORT).show();
                    }*/
                }else{
                    Toast.makeText(Registro.this,"Debe Llenar todos los Campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registerUser(){
        mAuth.createUserWithEmailAndPassword(CorreoElectronico, ContraseñadeUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombres", Nombres);
                    map.put("Apellidos", Apellidos);
                    map.put("Edad", Edades);
                    map.put("Correo Electronico", CorreoElectronico);
                    map.put("Contraseña", ContraseñadeUsuario);
                    String id = mAuth.getCurrentUser().getUid();
                    mDataReference.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                //startActivity(new Intent(RegistrodeClientes.this,Principal.class));
                                //Intent i = new Intent(RegistrodeClientes.this , Principal.class);
                                //startActivity(i);
                                //finish();
                            }else{
                                Toast.makeText(Registro.this,"No se pudieron crear los datos correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(Registro.this,"No se pudo registrar usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}