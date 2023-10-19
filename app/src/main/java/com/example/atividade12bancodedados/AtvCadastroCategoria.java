package com.example.atividade12bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtvCadastroCategoria extends AppCompatActivity implements View.OnClickListener
{
    Button btnGravar, btnCancelar, btnExcluir;
    EditText edtId, edtNome;

    String operacao;
    Categoria categoria;
    CategoriaDAO dao;

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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_cadastro_categoria);

        operacao = getIntent().getExtras().getString("operacao");
        dao = new CategoriaDAO(this);
        criarComponentes();

        if(getIntent().getExtras().getSerializable("categoria") != null)
        {
            categoria = (Categoria) getIntent().getExtras().getSerializable("categoria");
            edtId.setText(Integer.toString(categoria.getId()));
            edtNome.setText(categoria.getNome());
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
            int id = dao.excluir(categoria);
            Toast.makeText(this, "Categoria: " + categoria.getNome() + " foi excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (v == btnGravar)
        {
            categoria.setNome(edtNome.getText().toString());
            if (operacao.equals("Inserir"))
            {
                int id = dao.inserir(categoria);
                Toast.makeText(this, "Categoria: " + categoria.getNome() + " foi inserido com sucesso!", Toast.LENGTH_LONG).show();
            }
            else
            {
                int id = dao.alterar(categoria);
                Toast.makeText(this, "Categoria: " + categoria.getNome() + " foi alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}