package com.example.silascampos.acaosocial.Model;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Silas Campos on 10/10/2016.
 */
public class Instituicao{

    public static final int ASILO = 1;
    public static final int ORFANATO = 2;
    public static final int ABRIGO = 3;

    private int ID;

    private String foto;
    private String nome;
    private String descricao;
    private String endereco;
    private String doacoes;
    private String contato;
    private String responsavel;
    private double latitude;
    private double longitude;
    private int category;

    public Instituicao(String foto, String nome, String descricao, String endereco, String doacoes, String contato, String responsavel, double latitude, double longitude, int category) {
        this.foto = foto;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.doacoes = doacoes;
        this.contato = contato;
        this.responsavel = responsavel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }

    public Instituicao() {
        super();
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(String doacoes) {
        this.doacoes = doacoes;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
