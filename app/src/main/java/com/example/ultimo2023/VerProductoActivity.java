package com.example.ultimo2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimo2023.db.ProductoDB;
import com.example.ultimo2023.model.Producto;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

public class VerProductoActivity extends AppCompatActivity {
    EditText nombre, precio, descripcion, imagen;
    ImageView verImagen;
    Button editar, borrar, guardar;
    Producto producto;
    ProductoDB dbProductos;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        nombre = findViewById(R.id.etvNombre);
        precio = findViewById(R.id.etvPrecio);
        descripcion = findViewById(R.id.etvDescripcion);
        imagen = findViewById(R.id.etvImagen);
        verImagen = findViewById(R.id.imgVer);
        editar = findViewById(R.id.btnVerEditar);
        borrar = findViewById(R.id.btnVerBorrar);
        guardar = findViewById(R.id.btnVerGuardar);

        if(savedInstanceState == null){
            //recuperar datos desde otra activity
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                // ["ID", 451]
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        dbProductos = new ProductoDB(VerProductoActivity.this);
        producto = dbProductos.buscarProducto(id);
        if(producto != null){
            nombre.setText(producto.getNombre());
            precio.setText(String.valueOf(producto.getPrecio()));
            descripcion.setText(producto.getDescripcion());
            imagen.setText(producto.getImage());
            Picasso.get().load(producto.getImage()).into(verImagen);
        }
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarDatos();
            }
        });
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerProductoActivity.this);
                builder.setMessage("¿Desea eliminar este Producto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.borarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void editarDatos() {
        editar.setVisibility(View.INVISIBLE);
        nombre.setEnabled(true);
        precio.setEnabled(true);
        descripcion.setEnabled(true);
        imagen.setEnabled(true);
        guardar.setEnabled(true);
        guardar.setVisibility(View.VISIBLE);
    }

    private void guardarDatos() {
        if (!nombre.getText().toString().equals("") && !precio.getText().toString().equals("")
        && !descripcion.getText().toString().equals("") && !imagen.getText().toString().equals("")) {
           boolean correcto = dbProductos.editarProducto(id, nombre.getText().toString(), precio.getText().toString(),
                   descripcion.getText().toString(), imagen.getText().toString());

            if(correcto){
                Toast.makeText(VerProductoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                lista();
            } else {
                Toast.makeText(VerProductoActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(VerProductoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }
    }
}