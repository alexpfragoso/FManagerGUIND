package com.google.alexpfragoso.fmanagerguind;

/**
 * Created by Alex Fragoso on 23/02/2018.
 */

public class Pessoa {

    private String nome_completo;
    private String nome_usuario;
    private String senha;

    public Pessoa(String nome_completo, String nome_usuario, String senha) {
        this.nome_completo = nome_completo;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }

    public Pessoa(String nome_usuario, String senha) {
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome_completo() {

        return nome_completo;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public String getSenha() {
        return senha;
    }
}
