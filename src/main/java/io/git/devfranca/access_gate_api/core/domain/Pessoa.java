package io.git.devfranca.access_gate_api.core.domain;

public class Pessoa {

    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Pessoa(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    public Pessoa() {
    }
}
