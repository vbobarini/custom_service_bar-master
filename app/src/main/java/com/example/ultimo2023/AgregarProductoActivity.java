package com.example.ultimo2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultimo2023.db.ProductoDB;
import com.example.ultimo2023.model.Producto;

public class AgregarProductoActivity extends AppCompatActivity {
    EditText nombre, precio, imagen, descripcion;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        nombre = findViewById(R.id.edtAgNombre);
        precio = findViewById(R.id.edtAgPrecio);
        imagen = findViewById(R.id.edtAgImagen);
        descripcion = findViewById(R.id.edtAgDescripcion);
        btnAgregar = findViewById(R.id.btnAgregarProducto);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nombre.getText().toString().equals("") && !precio.getText().toString().equals("") &&
                !imagen.getText().toString().equals("") && !descripcion.getText().toString().equals("")) {
                    ProductoDB dbProdocutos = new ProductoDB(AgregarProductoActivity.this);
                    Producto p = new Producto(nombre.getText().toString(), Integer.parseInt(precio.getText().toString()), imagen.getText().toString(),descripcion.getText().toString());
                    boolean resp = dbProdocutos.agregarProducto(p);
                    System.out.println(dbProdocutos.seleccionProductos());
                    if(resp){
                        Toast.makeText(AgregarProductoActivity.this, "Producto Agregado correctamente.", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }else{
                        Toast.makeText(AgregarProductoActivity.this, "Fallo al agregar Producto.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AgregarProductoActivity.this, "Debe Llenar los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void limpiar() {
        nombre.setText("");
        imagen.setText("");
        precio.setText("");
        descripcion.setText("");
    }
}