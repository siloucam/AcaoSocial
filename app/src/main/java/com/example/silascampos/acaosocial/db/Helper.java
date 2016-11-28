package com.example.silascampos.acaosocial.db;

/**
 * Created by novaes on 14/10/16.
 */
import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.silascampos.acaosocial.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


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
                    Contracts.Visita.user_id + INTEGER_TYPE + COMMA_SEP +
                    Contracts.Visita.instituicao + TEXT_TYPE +
                    " )";

    public static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + Contracts.User.table + " (" +
                    Contracts.User.id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    Contracts.User.name + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.email + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.first_name + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.last_name + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.birthday + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.gender + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.password + TEXT_TYPE + COMMA_SEP +
                    Contracts.User.photo_url + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_INSTITUICAO =
            "DROP TABLE IF EXISTS " + Contracts.Instituicao.table;

    public static final String SQL_DELETE_VISITAS =
            "DROP TABLE IF EXISTS " + Contracts.Visita.table;

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + Contracts.User.table;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("myTag", String.valueOf("Salvar Imagens"));
        //String folder = "/sdcard/Pictures/MyAppFolder";

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_malonso);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        Log.d("myTag", String.valueOf(extStorageDirectory));
        File file = new File(extStorageDirectory, "logo_malonso.png");
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            Log.d("myTag", String.valueOf("Salvou"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        try {
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_orfanato);
        file = new File(extStorageDirectory, "logo_orfanato.png");
        try {
            outStream = new FileOutputStream(file);
            Log.d("myTag", String.valueOf("Salvou"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        try {
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.SQL_CREATE_INSTITUICAO_TABLE);
        db.execSQL(this.SQL_CREATE_VISITAS_TABLE);
        db.execSQL(this.SQL_CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.SQL_DELETE_INSTITUICAO);
        db.execSQL(this.SQL_DELETE_VISITAS);
        db.execSQL(this.SQL_DELETE_USER);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}