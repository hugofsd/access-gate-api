package io.git.devfranca.access_gate_api.core.service;

import io.git.devfranca.access_gate_api.core.domain.Morador;
import io.git.devfranca.access_gate_api.core.ports.MoradorRepositoryPort;
import io.git.devfranca.access_gate_api.core.ports.MoradorServicePort;

import java.util.Collection;

public class MoradorService implements MoradorServicePort {

    private final MoradorRepositoryPort moradorRepositoryPort;

    public MoradorService(MoradorRepositoryPort moradorRepositoryPort) {
        this.moradorRepositoryPort = moradorRepositoryPort;
    }

    @Override
    public Morador createMorador(Morador morador) {
        Morador moradorExistente = moradorRepositoryPort.obtainByCpf(morador.getCpf());
        if (moradorExistente != null) {
            throw  new IllegalArgumentException("Morador já existe");
        }
        return moradorRepositoryPort.create(morador);
    }

    @Override
    public Collection<Morador> findAll() {
        return moradorRepositoryPort.findAll();
    }
}
