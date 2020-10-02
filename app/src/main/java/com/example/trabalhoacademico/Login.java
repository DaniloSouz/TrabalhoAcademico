package com.example.trabalhoacademico;

import java.io.Serializable;

public class Login implements Serializable {
    private String Login, Senha;

    public Login(String login, String senha) {
        Login = login;
        Senha = senha;
    }

    public Login(){
        Login=null;
        Senha=null;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

}
