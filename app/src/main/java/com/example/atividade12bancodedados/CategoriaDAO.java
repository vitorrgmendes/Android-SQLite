package com.example.atividade12bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO
{
    private final String TABELA = "CATEGORIA";
    private final String[] CAMPOS = {"ID", "NOME"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public CategoriaDAO(Context context)
    {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Categoria categoria)
    {
        ContentValues valores = new ContentValues();

        valores.put("NOME", categoria.getNome());

        return valores;
    }

    public int inserir(Categoria categoria)
    {
        ContentValues valores = preencherValores(categoria);
        return (int) banco.insert(TABELA, null, valores);
    }

    public int alterar(Categoria categoria)
    {
        ContentValues valores = preencherValores(categoria);
        return banco.update(TABELA, valores, "id = ?", new String[] {Integer.toString(categoria.getId())});
    }

    public int excluir(Categoria categoria)
    {
        return banco.delete(TABELA, "id = ?", new String[] {Integer.toString(categoria.getId())});
    }

    public List<Categoria> listar()
    {
        Cursor cursor = banco.query(TABELA, CAMPOS,
                null, null, null, null, null);

        List<Categoria> lista = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Categoria categoria = new Categoria();
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            lista.add(categoria);
        }
        return lista;
    }
}
