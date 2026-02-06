package io.git.devfranca.access_gate_api.core.domain;

import java.time.LocalDateTime;

public class Visita {

    private Long id;
    private LocalDateTime datahora;
    private Long IdMorador;
    private Long IdVisitante;

    public Visita() {
    }

    public Visita(Long id, Long idVisitante, Long idMorador, LocalDateTime datahora) {
        this.id = id;
        IdVisitante = idVisitante;
        IdMorador = idMorador;
        this.datahora = datahora;
    }

    public LocalDateTime getDatahora() {
        return datahora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.datahora = datahora;
    }

    public Long getIdMorador() {
        return IdMorador;
    }

    public void setIdMorador(Long idMorador) {
        IdMorador = idMorador;
    }

    public Long getIdVisitante() {
        return IdVisitante;
    }

    public void setIdVisitante(Long idVisitante) {
        IdVisitante = idVisitante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
