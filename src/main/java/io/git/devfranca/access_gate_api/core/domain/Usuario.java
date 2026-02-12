package io.git.devfranca.access_gate_api.core.domain;

public class Usuario {

    private Long id;
    private String email;
    private String nome;
    private String senha;
    private Boolean administrador;
    private Pessoa pessoa;


    public Usuario(Long id, String nome, String email, String senha, Boolean administrador, Pessoa pessoa) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
        this.pessoa = pessoa;
    }

    public Usuario() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}


