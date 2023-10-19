package com.example.atividade12bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaMenu extends AppCompatActivity implements View.OnClickListener
{
    Button btnClientes, btnCategorias, btnProdutos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        inicializarComponentes();
    }

    private void inicializarComponentes()
    {
        btnClientes = findViewById(R.id.btnCliente);
        btnClientes.setOnClickListener(this);

        btnCategorias = findViewById(R.id.btnCategoria);
        btnCategorias.setOnClickListener(this);

        btnProdutos = findViewById(R.id.btnProduto);
        btnProdutos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnClientes)
        {
            Intent telaCliente = new Intent(TelaMenu.this, TelaCliente.class);
            startActivity(telaCliente);
        }
        else if (v == btnCategorias)
        {
            Intent telaCategoria = new Intent(TelaMenu.this, TelaCategoria.class);
            startActivity(telaCategoria);
        }
        else
        {
            Intent telaProduto = new Intent(TelaMenu.this, TelaProduto.class);
            startActivity(telaProduto);
        }
    }
}