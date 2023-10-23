package com.example.atividade12bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaCategoria extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener
{
    Button btnInserir;
    ListView lstCategoria;
    List<Categoria> listaCategoria = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    CategoriaDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_categoria);

        btnInserir = findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(this);

        lstCategoria = findViewById(R.id.lstCategoria);
        lstCategoria.setOnItemClickListener(this);

        dao = new CategoriaDAO(this);
        atualizarLista();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista()
    {
        listaCategoria = dao.listar();

        if (listaCategoria != null)
        {
            listAdapter = new ArrayAdapter<Categoria>(this,
                    android.R.layout.simple_list_item_1, listaCategoria);
            lstCategoria.setAdapter(listAdapter);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnInserir)
        {
            Categoria categoria = new Categoria();
            categoria.setId(0);
            abrirCadastro("Inserir", categoria);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        indice = position;
        Categoria categoria = (Categoria) lstCategoria.getAdapter().getItem(position);
        abrirCadastro("Alterar", categoria);
    }

    private void abrirCadastro(String operacao, Categoria categoria)
    {
        Intent telaCadastro = new Intent(this, AtvCadastroCategoria.class);
        Bundle extras = new Bundle();
        extras.putString("operacao", operacao);
        extras.putSerializable("categoria", categoria);
        telaCadastro.putExtras(extras);
        startActivity(telaCadastro);
    }
}