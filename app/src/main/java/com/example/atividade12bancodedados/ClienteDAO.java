package com.example.atividade12bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO
{
    private final String TABELA = "CLIENTE";
    private final String[] CAMPOS = {"ID", "NOME", "FONE", "EMAIL", "OBSERVACAO"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context)
    {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Cliente cliente)
    {
        ContentValues valores = new ContentValues();

        valores.put("NOME", cliente.getNome());
        valores.put("FONE", cliente.getFone());
        valores.put("EMAIL", cliente.getEmail());
        valores.put("OBSERVACAO", cliente.getObs());

        return valores;
    }

    public int inserir(Cliente cliente)
    {
        ContentValues valores = preencherValores(cliente);
        return (int) banco.insert(TABELA, null, valores);
    }

    public int alterar(Cliente cliente)
    {
        ContentValues valores = preencherValores(cliente);
        return banco.update(TABELA, valores, "id = ?", new String[] {Integer.toString(cliente.getId())});
    }

    public int excluir(Cliente cliente)
    {
        return banco.delete(TABELA, "id = ?", new String[] {Integer.toString(cliente.getId())});
    }

    public List<Cliente> listar()
    {
        Cursor cursor = banco.query(TABELA, CAMPOS,
                null, null, null, null, null);

        List<Cliente> lista = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setFone(cursor.getString(2));
            cliente.setEmail(cursor.getString(3));
            cliente.setObs(cursor.getString(4));
            lista.add(cliente);
        }
        return lista;
    }
}
