package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity
{

    Button btncriar;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btncriar = (Button) findViewById(R.id.btncriar);

        //p ir pra pagina de login
        btncriar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent it = new Intent(Inicio.this, MainActivity.class);
                startActivity(it);
            }
        });

        // 🔹 conecta com o XML
        recyclerView = findViewById(R.id.recyclerProdutos);

        // 🔹 define grid (quantas colunas quiser)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // 🔹 cria lista
        lista = new ArrayList<>();

        lista.add(new Product("Escola São Rodrigues", "Camiseta gola V", "Troca", Color.BLUE));
        lista.add(new Product("Colégio Objetivo", "Seminovo", "R$ 39", Color.MAGENTA));
        lista.add(new Product("Colégio Marisa", "Novo", "R$ 44", Color.RED));

        // 🔹 AQUI é o ponto importante 👇
        adapter = new ProductAdapter(lista);

        // 🔹 liga adapter no recycler
        recyclerView.setAdapter(adapter);
    }
}