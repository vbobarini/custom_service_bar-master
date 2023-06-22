package com.example.ultimo2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {

    Button btnAgregarProductos, btnMenu, btnAcercade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnAgregarProductos = findViewById(R.id.btnAgregarProducto);
        btnMenu = findViewById(R.id.btnMenu);
        btnAcercade = findViewById(R.id.btnAcercaDe);

        btnAgregarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioActivity.this, AgregarProductoActivity.class));
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioActivity.this, MenuActivity.class));
            }
        });
        btnAcercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( InicioActivity.this, ContactoActivity.class));
            }
        });

    }
}