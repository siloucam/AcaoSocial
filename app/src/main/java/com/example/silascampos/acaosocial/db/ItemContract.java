package com.example.silascampos.acaosocial.db;

import android.provider.BaseColumns;

/**
 * Created by novaes on 09/10/16.
 */


public final class ItemContract {

    public ItemContract() {}

    public static abstract class Item implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_STATE = "state";
    }
}
