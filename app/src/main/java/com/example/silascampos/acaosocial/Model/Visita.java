package com.example.silascampos.acaosocial.Model;

/**
 * Created by Silas on 18/10/2016.
 */
public class Visita {
    String instituicao;
    String data_txt;
    String hora_txt;
    int n_pessoas;

    public Visita(String instituicao, String data_txt, String hora_txt, int n_pessoas) {
        this.instituicao = instituicao;
        this.data_txt = data_txt;
        this.hora_txt = hora_txt;
        this.n_pessoas = n_pessoas;
    }
}
