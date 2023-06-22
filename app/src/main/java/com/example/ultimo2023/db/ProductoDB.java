package com.example.ultimo2023.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ultimo2023.model.Producto;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ProductoDB extends DbHelper {

    Context context;
    //constructor
    public ProductoDB(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    //alt intro
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTOS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " + "precio INTEGER, "+
                "imagen TEXT, " + "descripcion TEXT"+
                ");";
        //CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }

    //Checked
    public boolean agregarProducto(Producto p){
        boolean correcto = false;
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(COLUMN_NOMBRE, p.getNombre());
            values.put("precio", p.getPrecio());
            values.put("imagen", p.getImage());
            values.put("descripcion", p.getDescripcion());
            db.insert(TABLE_PRODUCTOS, null, values);
            correcto=true;
        }catch (Exception e){
            e.toString();
            correcto= false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public Producto buscarProducto(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Producto prod = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " +COLUMN_ID+ " = "+id+ " LIMIT 1",null);
        if (cursorProductos.moveToFirst()) {
            prod = new Producto();
            prod.setId(cursorProductos.getInt(0));
            prod.setNombre(cursorProductos.getString(1));
            prod.setPrecio(cursorProductos.getInt(2));
            prod.setImage(cursorProductos.getString(3));
            prod.setDescripcion(cursorProductos.getString(4));
        }
        cursorProductos.close();
        return prod;
    }
    public boolean borarProducto(int id){
        boolean correcto;
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE "+COLUMN_ID+ " =\""+ id +"\";");
            correcto=true;
        }catch (Exception e){
            e.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
    public boolean editarProducto(int id, String nombre, String precio, String desc, String urlImagen){
        boolean correcto = false;
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("UPDATE "+ TABLE_PRODUCTOS + " SET nombre = '" + nombre
                    + "', precio = '" + precio + "', imagen = '" + urlImagen + "', descripcion = '" + desc + "' WHERE id='" + id + "' ");
            correcto = true;
        }catch (Exception e){
            e.toString();
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }
    public ArrayList<Producto> mostrarProductos(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto;
        Cursor cursorProducto;

        cursorProducto = db.rawQuery("SELECT * FROM productos", null);
        if(cursorProducto.moveToFirst()){
            do{
              producto = new Producto();
              producto.setId(cursorProducto.getInt(0));
              producto.setNombre(cursorProducto.getString(1));
              producto.setPrecio(cursorProducto.getInt(2));
              producto.setImage(cursorProducto.getString(3));
              producto.setDescripcion(cursorProducto.getString(4));
              System.out.println("mostrar: "+ cursorProducto.getString(3) + cursorProducto.getString(1));
              listaProductos.add(producto);

            }while(cursorProducto.moveToNext());
        }
        cursorProducto.close();
        return listaProductos;
    }
    @SuppressLint("Range")
    public String seleccionProductos(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_PRODUCTOS + " WHERE 1";
        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        while (!result.isAfterLast()){
            if(result.getString(result.getColumnIndex("nombre")) !=null){
                dbString += result.getString(result.getColumnIndex("id")) + " " +
                        result.getString(result.getColumnIndex("nombre"));
                dbString += "\n";
            }
            result.moveToNext();
        }
        db.close();
        return dbString;
    }
}
