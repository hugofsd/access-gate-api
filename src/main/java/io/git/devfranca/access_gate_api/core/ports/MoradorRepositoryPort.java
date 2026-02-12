package io.git.devfranca.access_gate_api.core.ports;

import io.git.devfranca.access_gate_api.core.domain.Morador;

import java.util.Collection;

public interface MoradorRepositoryPort {

    public Morador create(Morador morador);

    public Morador obtainByCpf(String cpf);

    public Collection<Morador> findAll();
}
