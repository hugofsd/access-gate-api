package io.git.devfranca.access_gate_api.core.domain;


public class Morador {

    private Long id;
    private String cpf;
    private String endereco;
    private String celular;
    private Long IdPessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getIdPessoa() {
        return IdPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        IdPessoa = idPessoa;
    }

    public Morador(Long id, Long idPessoa, String celular, String endereco, String cpf) {
        this.id = id;
        IdPessoa = idPessoa;
        this.celular = celular;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public Morador() {
    }
}
