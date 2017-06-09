package com.supero.rausch.contatos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.supero.rausch.contatos.models.Contato;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rausch on 08/06/2017.
 */

public class ContatoRepository {

    private SQLiteHelper helper;

    public ContatoRepository(Context context) {
        helper = new SQLiteHelper(context);
    }

    public List<Contato> getAll() {
        helper.open();
        Cursor cursor = helper.getDatabase().rawQuery("SELECT * FROM " + SQLiteHelper.TABELA_CONTATO, null);
        List<Contato> contatos = new ArrayList<Contato>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Contato contato = new Contato(
                        cursor.getLong(cursor.getColumnIndex(SQLiteHelper.CAMPO_ID)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_NOME)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_CEP)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_LOGRADOURO)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_NUMERO)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_BAIRRO)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_CIDADE)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_UF)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_TELEFONE)),
                        cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_COMPLEMENTO))
                );

                contatos.add(contato);

                cursor.moveToNext();
            }
        }
        cursor.close();
        helper.close();
        return contatos;
    }

    public Contato getById(long id) {
        Contato result = null;
        helper.open();
        Cursor cursor = helper.getDatabase().rawQuery("SELECT * FROM " + SQLiteHelper.TABELA_CONTATO + " WHERE id = " + id, null);
        if (cursor.moveToFirst()) {
            result = new Contato(
                    cursor.getLong(cursor.getColumnIndex(SQLiteHelper.CAMPO_ID)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_NOME)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_CEP)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_LOGRADOURO)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_NUMERO)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_BAIRRO)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_CIDADE)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_UF)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_TELEFONE)),
                    cursor.getString(cursor.getColumnIndex(SQLiteHelper.CAMPO_COMPLEMENTO))
            );
        }
        cursor.close();
        helper.close();
        return result;
    }

    public void save(Contato contato) {
        if (contato.getId() > 0) {
            update(contato);
        } else {
            insert(contato);
        }
    }

    public long insert(Contato contato) {
        helper.open();
        ContentValues values = makeContentValues(contato);
        long result = helper.getDatabase()
                .insert(
                        SQLiteHelper.TABELA_CONTATO,
                        null,
                        values
                );
        helper.close();
        return result;
    }

    private ContentValues makeContentValues(Contato contato) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.CAMPO_NOME, contato.getNome());
        values.put(SQLiteHelper.CAMPO_EMAIL, contato.getEmail());
        values.put(SQLiteHelper.CAMPO_CEP, contato.getCep());
        values.put(SQLiteHelper.CAMPO_LOGRADOURO, contato.getLogradouro());
        values.put(SQLiteHelper.CAMPO_NUMERO, contato.getNumero());
        values.put(SQLiteHelper.CAMPO_BAIRRO, contato.getBairro());
        values.put(SQLiteHelper.CAMPO_CIDADE, contato.getCidade());
        values.put(SQLiteHelper.CAMPO_UF, contato.getUf());
        values.put(SQLiteHelper.CAMPO_TELEFONE, contato.getTelefone());
        values.put(SQLiteHelper.CAMPO_COMPLEMENTO, contato.getComplemento());
        return values;
    }

    public int update(Contato contato) {
        helper.open();
        ContentValues values = makeContentValues(contato);
        int result = helper.getDatabase()
                .update(
                        SQLiteHelper.TABELA_CONTATO,
                        values,
                        SQLiteHelper.CAMPO_ID + " = " + contato.getId(),
                        null
                );
        helper.close();
        return result;
    }

    public void delete(Contato contato) {
        helper.open();
        helper.getDatabase()
                .delete(
                        SQLiteHelper.TABELA_CONTATO,
                        SQLiteHelper.CAMPO_ID + " = " + contato.getId(),
                        null
                );
        helper.close();
    }
}
