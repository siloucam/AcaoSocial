package com.example.silascampos.acaosocial.db;

import android.provider.BaseColumns;

/**
 * Created by novaes on 14/10/16.
 */
public final class Contracts {

        public static abstract class Instituicao implements BaseColumns {
            public static final String table = "instituicao";
            public static final int ID = 0;
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
            public static final int n_pessoas = 0;
            public static final int ID = 0;
        }

}
