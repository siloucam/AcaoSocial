package com.example.silascampos.acaosocial;

/**
 * Created by Silas Campos on 10/10/2016.
 */
public class Instituicao{

    public Instituicao(){
        new Instituicao();
    }


    private String foto;
    private String nome;
    private String descricao;

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
}
