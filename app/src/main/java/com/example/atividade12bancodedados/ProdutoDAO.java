package com.example.atividade12bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO
{
    private final String TABELA = "PRODUTO";
    private final String[] CAMPOS = {"ID", "NOME", "CUSTO", "PRECOVENDA", "UNIDADE", "QUANTIDADE", "IDCATEGORIA"};
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context)
    {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Produto produto)
    {
        ContentValues valores = new ContentValues();

        valores.put("NOME", produto.getNome());
        valores.put("CUSTO", produto.getCusto());
        valores.put("PRECOVENDA", produto.getPrecoVenda());
        valores.put("UNIDADE", produto.getUnidade());
        valores.put("QUANTIDADE", produto.getQuantidade());
        valores.put("IDCATEGORIA", produto.getIdCategoria());

        return valores;
    }

    public int inserir(Produto produto)
    {
        ContentValues valores = preencherValores(produto);
        return (int) banco.insert(TABELA, null, valores);
    }

    public int alterar(Produto produto)
    {
        ContentValues valores = preencherValores(produto);
        return banco.update(TABELA, valores, "id = ?", new String[] {Integer.toString(produto.getId())});
    }

    public int excluir(Produto produto)
    {
        return banco.delete(TABELA, "id = ?", new String[] {Integer.toString(produto.getId())});
    }

    public List<Produto> listar()
    {
        Cursor cursor = banco.query(TABELA, CAMPOS,
                null, null, null, null, null);

        List<Produto> lista = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome(cursor.getString(1));
            produto.setCusto(cursor.getDouble(2));
            produto.setPrecoVenda(cursor.getDouble(3));
            produto.setUnidade(cursor.getString(4));
            produto.setQuantidade(cursor.getInt(5));
            produto.setIdCategoria(cursor.getInt(6));
            lista.add(produto);
        }
        return lista;
    }
}
