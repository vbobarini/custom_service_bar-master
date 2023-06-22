package com.example.ultimo2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView txtPasswordRecovery, txtRegister;
    EditText edtEmail, edtPassword;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance(); //conecta con firebase auth

        txtRegister = findViewById(R.id.txtRegister);
        txtPasswordRecovery = findViewById(R.id.txtPasswordRecovery);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFirebase();
            }
        });
    }

    private void loginFirebase() {

          firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
              @Override
              public void onSuccess(AuthResult authResult) {
                  Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                  finish();
                  startActivity(new Intent(MainActivity.this, InicioActivity.class));

              }

          }).addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                  Toast.makeText(MainActivity.this, "Falló el Inicio de Sesión", Toast.LENGTH_LONG).show();
              }
          });
    }
}