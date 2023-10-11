package com.example.atividade12bancodedados;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AtvCadastro extends AppCompatActivity implements View.OnClickListener
{
    Button btnGravar, btnCancelar, btnExcluir;
    EditText edtId, edtNome, edtFone, edtEmail, edtObs;

    String operacao;
    Cliente cliente;
    ClienteDAO dao;

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
        edtFone = findViewById(R.id.edtFone);
        edtEmail = findViewById(R.id.edtEmail);
        edtObs = findViewById(R.id.edtObs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_cadastro);

        operacao = getIntent().getExtras().getString("operacao");
        dao = new ClienteDAO(this);
        criarComponentes();

        if(getIntent().getExtras().getSerializable("cliente") != null)
        {
            cliente = (Cliente) getIntent().getExtras().getSerializable("cliente");
            edtId.setText(Integer.toString(cliente.getId()));
            edtNome.setText(cliente.getNome());
            edtFone.setText(cliente.getFone());
            edtEmail.setText(cliente.getEmail());
            edtObs.setText(cliente.getObs());
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
            int id = dao.excluir(cliente);
            Toast.makeText(this, "Cliente: " + cliente.getNome() + " foi excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (v == btnGravar)
        {
            cliente.setNome(edtNome.getText().toString());
            cliente.setFone(edtFone.getText().toString());
            cliente.setEmail(edtEmail.getText().toString());
            cliente.setObs(edtObs.getText().toString());

            if (operacao.equals("Inserir"))
            {
                int id = dao.inserir(cliente);
                Toast.makeText(this, "Cliente: " + cliente.getNome() + " foi inserido com sucesso!", Toast.LENGTH_LONG).show();
            }
            else
            {
                int id = dao.alterar(cliente);
                Toast.makeText(this, "Cliente: " + cliente.getNome() + " foi alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}