package com.supero.rausch.contatos.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rausch on 08/06/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String NOME_DO_BANCO = "exemplosqlite";
    public static final int VERSAO_DO_BANCO = 1;
    public static final String TABELA_CONTATO = "contato";
    //Nome dos campos
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_CEP = "cep";
    public static final String CAMPO_LOGRADOURO = "logradouro";
    public static final String CAMPO_NUMERO = "numero";
    public static final String CAMPO_COMPLEMENTO = "complemento";
    public static final String CAMPO_BAIRRO = "bairro";
    public static final String CAMPO_CIDADE = "cidade";
    public static final String CAMPO_UF = "uf";
    public static final String CAMPO_TELEFONE = "telefone";
    private static final String CRIAR_TABELA_CONTATO = "CREATE TABLE "
            + TABELA_CONTATO + " (" + CAMPO_ID + " INTEGER PRIMARY KEY, "
            + CAMPO_NOME + " TEXT, "
            + CAMPO_EMAIL + " TEXT, "
            + CAMPO_CEP + " TEXT, "
            + CAMPO_LOGRADOURO + " TEXT, "
            + CAMPO_NUMERO + " TEXT, "
            + CAMPO_COMPLEMENTO + " TEXT, "
            + CAMPO_BAIRRO + " TEXT, "
            + CAMPO_CIDADE + " TEXT, "
            + CAMPO_UF + " TEXT, "
            + CAMPO_TELEFONE + " TEXT)";
    private SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_TABELA_CONTATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void open() throws SQLException {
        database = getWritableDatabase();
    }

    public void close() {
        if (database.isOpen())
            database.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
