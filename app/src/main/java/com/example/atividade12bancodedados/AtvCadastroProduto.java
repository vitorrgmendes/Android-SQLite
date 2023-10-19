package com.example.atividade12bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtvCadastroProduto extends AppCompatActivity implements View.OnClickListener
{
    Button btnGravar, btnCancelar, btnExcluir;
    EditText edtId, edtNome, edtCusto, edtPrecoVenda, edtUnidade, edtQuantidade, edtIdCategoria;

    String operacao;
    Produto produto;
    ProdutoDAO dao;

    private void criarComponentes()
    {
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
        btnGravar = findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);
        if(operacao.equals("Inserir"))
        {
            btnExcluir.setVisibility(View.INVISIBLE);
            btnGravar.setText("Gravar");
        }
        else
        {
            btnExcluir.setVisibility(View.VISIBLE);
            btnGravar.setText("Alterar");
        }
        edtId = findViewById(R.id.edtId);
        edtNome = findViewById(R.id.edtNome);
        edtCusto = findViewById(R.id.edtCusto);
        edtPrecoVenda = findViewById(R.id.edtPrecoVenda);
        edtUnidade = findViewById(R.id.edtUnidade);
        edtQuantidade = findViewById(R.id.edtQuantidade);
        edtIdCategoria = findViewById(R.id.edtIdCategoria);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_cadastro_produto);

        operacao = getIntent().getExtras().getString("operacao");
        dao = new ProdutoDAO(this);
        criarComponentes();

        if(getIntent().getExtras().getSerializable("produto") != null)
        {
            produto = (Produto) getIntent().getExtras().getSerializable("produto");
            edtId.setText(Integer.toString(produto.getId()));
            edtNome.setText(produto.getNome());
            edtCusto.setText(Double.toString(produto.getCusto()));
            edtPrecoVenda.setText(Double.toString(produto.getPrecoVenda())); ;
            edtUnidade.setText(produto.getUnidade()); ;
            edtQuantidade.setText(Integer.toString(produto.getQuantidade()));
            edtIdCategoria.setText(Integer.toString(produto.getIdCategoria()));
        }
    }

    @Override
    public void onClick(View v)
    {
        if (v == btnCancelar)
        {
            finish();
        }
        else if (v == btnExcluir)
        {
            int id = dao.excluir(produto);
            Toast.makeText(this, "Produto: " + produto.getNome() + " foi excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (v == btnGravar)
        {
            produto.setNome(edtNome.getText().toString());
            produto.setCusto(Double.parseDouble(edtCusto.getText().toString()));
            produto.setPrecoVenda(Double.parseDouble(edtPrecoVenda.getText().toString()));
            produto.setUnidade(edtUnidade.getText().toString());
            produto.setQuantidade(Integer.parseInt(edtQuantidade.getText().toString()));
            produto.setIdCategoria(Integer.parseInt(edtIdCategoria.getText().toString()));

            if (operacao.equals("Inserir"))
            {
                int id = dao.inserir(produto);
                Toast.makeText(this, "Produto: " + produto.getNome() + " foi inserido com sucesso!", Toast.LENGTH_LONG).show();
            }
            else
            {
                int id = dao.alterar(produto);
                Toast.makeText(this, "Produto: " + produto.getNome() + " foi alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}