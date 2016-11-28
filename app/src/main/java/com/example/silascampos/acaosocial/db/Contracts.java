package com.example.silascampos.acaosocial.db;

import android.provider.BaseColumns;

public final class Contracts {

        public static abstract class Instituicao implements BaseColumns {
            public static final String table = "instituicao";
            public static final String foto = "foto";
            public static final String nome = "nome";
            public static final String descricao = "descricao";
            public static final String endereco = "endereco";
            public static final String doacoes = "doacoes";
            public static final String contato = "contato";
            public static final String responsavel = "responsavel";
        }
    
        public static abstract class Visita implements BaseColumns {
            public static final String table = "visita";
            public static final String instituicao = "instituicao";
            public static final String data_txt = "data";
            public static final String hora_txt = "hora";
            public static final String n_pessoas = "n_pessoas";
            public static final String user_id = "user_id";
        }

    public static abstract class User implements BaseColumns {
        public static final String table = "user";
        public static final String name = "name";
        public static final String email = "email";
        public static final String photo_url = "photo_url";
        public static final String password = "password";
        public static final String id = "id";
        public static final String first_name = "first_name";
        public static final String last_name = "last_name";
        public static final String gender = "gender";
        public static final String birthday = "birthday";
    }
}
