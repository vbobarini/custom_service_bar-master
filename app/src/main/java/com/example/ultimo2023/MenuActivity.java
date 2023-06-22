package com.example.ultimo2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ultimo2023.Adapter.ProductoAdapter;
import com.example.ultimo2023.db.ProductoDB;
import com.example.ultimo2023.model.Producto;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ArrayList<Producto> listaProducto;
    RecyclerView recyclerView;
    ProductoAdapter adapterProducto;
    ProductoDB dbProducto;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerProducto);
        btnAgregar = findViewById(R.id.btnMenuAgregar);
        listaProducto = new ArrayList<>();
        dbProducto = new ProductoDB(MenuActivity.this);

        adapterProducto = new ProductoAdapter(dbProducto.mostrarProductos());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterProducto);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, AgregarProductoActivity.class));
            }
        });
    }
}