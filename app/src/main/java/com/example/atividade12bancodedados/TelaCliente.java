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

public class TelaCliente extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener
{
    Button btnInserir, btnCancelar;
    ListView lstClientes;
    List<Cliente> listaClientes = new ArrayList<>();
    ListAdapter listAdapter;
    int indice;
    ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);

        btnInserir = findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(this);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);

        lstClientes = findViewById(R.id.lstClientes);
        lstClientes.setOnItemClickListener(this);

        dao = new ClienteDAO(this);
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
        listaClientes = dao.listar();
        listAdapter = new ArrayAdapter<Cliente>(this,
                android.R.layout.simple_list_item_1, listaClientes);
        lstClientes.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnInserir)
        {
            Cliente cliente = new Cliente();
            cliente.setId(0);
            abrirCadastro("Inserir", cliente);
        }
        else if (v == btnCancelar)
        {
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        indice = position;
        Cliente cliente = (Cliente) lstClientes.getAdapter().getItem(position);
        abrirCadastro("Alterar", cliente);
    }

    private void abrirCadastro(String operacao, Cliente cliente)
    {
        Intent telaCadastro = new Intent(this, AtvCadastroCliente.class);
        Bundle extras = new Bundle();
        extras.putString("operacao", operacao);
        extras.putSerializable("cliente", cliente);
        telaCadastro.putExtras(extras);
        startActivity(telaCadastro);
    }
}