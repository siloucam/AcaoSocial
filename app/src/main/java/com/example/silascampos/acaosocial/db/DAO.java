package com.example.silascampos.acaosocial.db;

/**
 * Created by novaes on 14/10/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAO {


    SQLiteDatabase db;
    Helper mDbHelper;

    public DAO(Context context){
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

    public void putVisita(String instituicao, String data_txt, String hora_txt, int n){
        this.open("write");

        ContentValues values = new ContentValues();

        values.put(Contracts.Visita.instituicao, instituicao);
        values.put(Contracts.Visita.data_txt, data_txt);
        values.put(Contracts.Visita.hora_txt, hora_txt);
        values.put(Contracts.Visita.n_pessoas, n);

        long newRowId;
        newRowId = db.insert(
                Contracts.Visita.table,
                null,
                values);

        this.close();
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

