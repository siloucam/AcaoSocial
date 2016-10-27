package com.example.silascampos.acaosocial.db;

/**
 * Created by novaes on 14/10/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Maps.db";

    public static final String TEXT_TYPE = " TEXT";

    public static final String DOUBLE_TYPE = " DOUBLE";

    public static final String INTEGER_TYPE = " INTEGER";

    public static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_INSTITUICAO_TABLE =
            "CREATE TABLE " + Contracts.Instituicao.table + " (" +
                    Contracts.Instituicao._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    Contracts.Instituicao.foto + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.nome + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.descricao + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.endereco + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.doacoes + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.contato + TEXT_TYPE + COMMA_SEP +
                    Contracts.Instituicao.responsavel + TEXT_TYPE +
                    " )";

    public static final String SQL_CREATE_VISITAS_TABLE =
            "CREATE TABLE " + Contracts.Visita.table + " (" +
                    Contracts.Visita._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    Contracts.Visita.data_txt + TEXT_TYPE + COMMA_SEP +
                    Contracts.Visita.hora_txt + TEXT_TYPE + COMMA_SEP +
                    Contracts.Visita.n_pessoas + TEXT_TYPE + COMMA_SEP +
                    Contracts.Visita.instituicao + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_INSTITUICAO =
            "DROP TABLE IF EXISTS " + Contracts.Instituicao.table;

    public static final String SQL_DELETE_VISITAS =
            "DROP TABLE IF EXISTS " + Contracts.Visita.table;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.SQL_CREATE_INSTITUICAO_TABLE);
        db.execSQL(this.SQL_CREATE_VISITAS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.SQL_DELETE_INSTITUICAO);
        db.execSQL(this.SQL_DELETE_VISITAS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}