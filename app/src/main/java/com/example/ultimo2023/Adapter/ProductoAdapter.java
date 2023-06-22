package com.example.ultimo2023.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimo2023.R;
import com.example.ultimo2023.VerProductoActivity;
import com.example.ultimo2023.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHoler> {

    ArrayList<Producto> listaProductos;

    public ProductoAdapter(ArrayList<Producto> listProd) {
        this.listaProductos = listProd;
    }

    @NonNull
    @Override
    public ProductoViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.producto_item, parent, false);
        return new ProductoViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHoler holder, int position) {
        Producto p = listaProductos.get(position);
        holder.nombreP.setText(p.getNombre());
        Picasso.get().load(p.getImage()).into(holder.imgProduct);
        holder.precioP.setText("$ "+ p.getPrecio());
        holder.detalleP.setText(p.getDescripcion());
        System.out.println("mostrar: "+p.getImage());

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHoler extends RecyclerView.ViewHolder {

        TextView nombreP, precioP, detalleP;
        ImageView imgProduct;

        public ProductoViewHoler(@NonNull View itemView) {
            super(itemView);

            nombreP = itemView.findViewById(R.id.etNombreRec);
            precioP = itemView.findViewById(R.id.etPrecioRec);
            detalleP = itemView.findViewById(R.id.etDescProduct);
            imgProduct = itemView.findViewById(R.id.imgProductoRec);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerProductoActivity.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
