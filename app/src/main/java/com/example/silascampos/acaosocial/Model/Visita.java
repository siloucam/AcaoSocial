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

    public Visita() {

    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getData_txt() {
        return data_txt;
    }

    public void setData_txt(String data_txt) {
        this.data_txt = data_txt;
    }

    public String getHora_txt() {
        return hora_txt;
    }

    public void setHora_txt(String hora_txt) {
        this.hora_txt = hora_txt;
    }

    public int getN_pessoas() {
        return n_pessoas;
    }

    public void setN_pessoas(int n_pessoas) {
        this.n_pessoas = n_pessoas;
    }
}
