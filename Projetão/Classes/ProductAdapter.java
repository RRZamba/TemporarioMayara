package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> lista;

    public ProductAdapter(List<Product> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descricao, preco;
        View imgColor;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descricao);
            preco = itemView.findViewById(R.id.preco);
            imgColor = itemView.findViewById(R.id.imgColor);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product p = lista.get(position);

        holder.titulo.setText(p.titulo);
        holder.descricao.setText(p.descricao);
        holder.preco.setText(p.preco);
        holder.imgColor.setBackgroundColor(p.cor);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}