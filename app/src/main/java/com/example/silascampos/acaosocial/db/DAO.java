package com.example.silascampos.acaosocial.db;

/**
 * Created by novaes on 14/10/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.silascampos.acaosocial.Model.Instituicao;
import com.example.silascampos.acaosocial.Model.User;
import com.example.silascampos.acaosocial.Model.Visita;

import java.io.IOException;
import java.util.ArrayList;

public class DAO {


    SQLiteDatabase db;
    Helper mDbHelper;

    public DAO(Context context) throws IOException {
        mDbHelper = new Helper(context);
    }

    public void open(String mode){
        if(mode.equals("write")) {
            db = mDbHelper.getWritableDatabase();
        }else{
            db = mDbHelper.getReadableDatabase();
        }
    }

    public void close(){
        db.close();
    }

    public void putVisita(String instituicao, String data_txt, String hora_txt, String n, long user_id){
        this.open("write");

        ContentValues values = new ContentValues();

        values.put(Contracts.Visita.instituicao, instituicao);
        values.put(Contracts.Visita.data_txt, data_txt);
        values.put(Contracts.Visita.hora_txt, hora_txt);
        values.put(Contracts.Visita.n_pessoas, n);
        values.put(Contracts.Visita.user_id, user_id);

        long newRowId;
        newRowId = db.insert(
                Contracts.Visita.table,
                null,
                values);

        this.close();
    }

    public void putInstituicao(String nome, String foto, String descricao, String endereco, String doacoes, String contato, String responsavel, double latitude, double longitude, int category){
        this.open("write");

        ContentValues values = new ContentValues();

        values.put(Contracts.Instituicao.nome, nome);
        values.put(Contracts.Instituicao.foto, foto);
        values.put(Contracts.Instituicao.descricao, descricao);
        values.put(Contracts.Instituicao.endereco, endereco);
        values.put(Contracts.Instituicao.doacoes, doacoes);
        values.put(Contracts.Instituicao.contato, contato);
        values.put(Contracts.Instituicao.responsavel, responsavel);
        values.put(Contracts.Instituicao.latitude, latitude);
        values.put(Contracts.Instituicao.longitude, longitude);
        values.put(Contracts.Instituicao.category, category);

        long newRowId;
        newRowId = db.insert(
                Contracts.Instituicao.table,
                null,
                values);

        this.close();
    }


    public Cursor getVisitas(String nome){
        this.open("read");

        String[] projection = {
                Contracts.Visita._ID,
                Contracts.Visita.instituicao,
                Contracts.Visita.data_txt,
                Contracts.Visita.hora_txt,
                Contracts.Visita.n_pessoas,
                Contracts.Visita.user_id
        };

        String[] arguments = {nome};

        String sortOrder =
                Contracts.Visita.data_txt+ " ASC";

        String selection =  Contracts.Visita.instituicao + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = nome;


        Cursor c = db.query(
                Contracts.Visita.table,                   // The table to query
                projection,                               // The columns to return
                selection,             // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }

    public Cursor getVisitasByUser(long user_id){
        this.open("read");

        String id = Integer.toString((int) user_id);

        String[] projection = {
                Contracts.Visita._ID,
                Contracts.Visita.instituicao,
                Contracts.Visita.data_txt,
                Contracts.Visita.hora_txt,
                Contracts.Visita.n_pessoas,
                Contracts.Visita.user_id
        };

        String[] arguments = {id};

        String sortOrder =
                Contracts.Visita.data_txt+ " ASC";

        String selection =  Contracts.Visita.user_id + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = id;


        Cursor c = db.query(
                Contracts.Visita.table,                   // The table to query
                projection,                               // The columns to return
                selection,             // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }

    public long putUser(String name, String email, String photo_url, String password, String first_name, String last_name, String gender, String birthday){
        this.open("write");

        ContentValues values = new ContentValues();

        values.put(Contracts.User.name, name);
        values.put(Contracts.User.email, email);
        values.put(Contracts.User.photo_url, photo_url);
        values.put(Contracts.User.password, password);
        values.put(Contracts.User.first_name, first_name);
        values.put(Contracts.User.last_name, last_name);
        values.put(Contracts.User.gender, gender);
        values.put(Contracts.User.birthday, birthday);

        long newRowId;
        newRowId = db.insert(
                Contracts.User.table,
                null,
                values);

        this.close();
        return newRowId;
    }

    public User getUser(String id){
        this.open("read");

        String[] projection = {
                Contracts.User.id,
                Contracts.User.name,
                Contracts.User.photo_url,
        };

        String[] arguments = {id};

        String sortOrder =
                Contracts.Visita.data_txt+ " ASC";

        String selection =  Contracts.User.id + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = id;

        Cursor c = db.query(
                Contracts.User.table,                   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if(c!=null) {
            User user = new User(c.getLong(1), c.getString(2), c.getString(3));
            return user;
        }else{
            return null;
        }
    }

    public User getUserByName(String name){
        this.open("read");

        String[] projection = {
                Contracts.User.id,
                Contracts.User.name,
                Contracts.User.photo_url,
        };

        String[] arguments = {name};

        String sortOrder =
                Contracts.Visita.data_txt+ " ASC";

        String selection =  Contracts.User.name + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = name;

        Cursor c = db.query(
                Contracts.User.table,                   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        if(c!=null) {
            User user = new User(c.getLong(1), c.getString(2), c.getString(3));
            return user;
        }else{
            return null;
        }
    }

    public ArrayList<Instituicao>  getLocations(){
        this.open("read");

        String[] projection = {
                Contracts.Instituicao._ID,
                Contracts.Instituicao.foto,
                Contracts.Instituicao.nome,
                Contracts.Instituicao.descricao,
                Contracts.Instituicao.endereco,
                Contracts.Instituicao.contato,
                Contracts.Instituicao.responsavel,
                Contracts.Instituicao.latitude,
                Contracts.Instituicao.longitude,
                Contracts.Instituicao.category
        };

        Cursor c = db.query(
                Contracts.Instituicao.table,  // The table to query
                projection,                               // The columns to return
                null,       // The columns for the WHERE clause
                null,  // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Instituicao> List = new ArrayList<>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Instituicao l = new Instituicao(c.getString(1), c.getString(2), c.getString(3), c.getString(4),"doacoes", c.getString(5),c.getString(6), c.getDouble(7),c.getDouble(8),c.getInt(9));
            List.add(l);

            /*
            Log.i("rows",c.getString(1));
            Log.i("rows",c.getString(2));
            Log.i("rows",c.getString(3));
            Log.i("rows",c.getString(4));
            Log.i("rows",c.getString(5));
            Log.i("rows",c.getString(6));
            */

        }

        this.close();

        return List;
    }




    /*
    public void putLocation(double latitude, double longitude,String label, int category){
        this.open("write");

        ContentValues values = new ContentValues();
        values.put(Contracts.Location.latitude, latitude);
        values.put(Contracts.Location.longitude, longitude);
        values.put(Contracts.Location.label, label);
        values.put(Contracts.Location.category, category);

        long newRowId;
        newRowId = db.insert(
                Contracts.Location.table,
                null,
                values);

        this.close();
    }

    public ArrayList<Location>  getLocations(int category){
        this.open("read");

        String[] projection = {
                Contracts.Location._ID,
                Contracts.Location.latitude,
                Contracts.Location.longitude,
                Contracts.Location.label,
                Contracts.Location.category
        };

        String filterCols = Contracts.Location.category+" = ?";

        String sortOrder =
                Contracts.Location.label+ " ASC";

        String filterValues[] = new String[] {String.valueOf(category)};

        if(category == 0){
            filterCols = null;
            filterValues = null;
        }

        Cursor c = db.query(
                Contracts.Location.table,  // The table to query
                projection,                               // The columns to return
                filterCols,       // The columns for the WHERE clause
                filterValues,  // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Location> List = new ArrayList<Location>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Location l = new Location(c.getDouble(1), c.getDouble(2),c.getString(3),c.getInt(4));
            List.add(l);
        }

        this.close();

        return List;
    }


    public void putCategory(String label){
        this.open("write");

        ContentValues values = new ContentValues();
        values.put(Contracts.Category.label, label);

        long newRowId;
        newRowId = db.insert(
                Contracts.Category.table,
                null,
                values);

        this.close();
    }


    public ArrayList<Category>  getCategories(){
        this.open("read");

        String[] projection = {
                Contracts.Category._ID,
                Contracts.Category.label
        };

        String sortOrder =
                Contracts.Category.label+ " ASC";

        Cursor c = db.query(
                Contracts.Category.table,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<Category> List = new ArrayList<Category>();
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            Category l = new Category(c.getInt(0),c.getString(1));
            List.add(l);
        }

        this.close();

        return List;
    }
    */

}

