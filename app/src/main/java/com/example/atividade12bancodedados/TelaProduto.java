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

public class TelaProduto extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener
{
    Button btnInserir;
    ListView lstProduto;
    List<Produto> listaProduto = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    ProdutoDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produto);

        btnInserir = findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(this);

        lstProduto = findViewById(R.id.lstProduto);
        lstProduto.setOnItemClickListener(this);

        dao = new ProdutoDAO(this);
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
        listaProduto = dao.listar();
        listAdapter = new ArrayAdapter<Produto>(this,
                android.R.layout.simple_list_item_1, listaProduto);
        lstProduto.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnInserir)
        {
            Produto produto = new Produto();
            produto.setId(0);
            abrirCadastro("Inserir", produto);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        indice = position;
        Produto produto = (Produto) lstProduto.getAdapter().getItem(position);
        abrirCadastro("Alterar", produto);
    }

    private void abrirCadastro(String operacao, Produto produto)
    {
        Intent telaCadastro = new Intent(this, AtvCadastroProduto.class);
        Bundle extras = new Bundle();
        extras.putString("operacao", operacao);
        extras.putSerializable("produto", produto);
        telaCadastro.putExtras(extras);
        startActivity(telaCadastro);
    }
}