package io.git.devfranca.access_gate_api.core.ports;

import io.git.devfranca.access_gate_api.core.domain.Morador;

import java.util.Collection;

public interface MoradorServicePort {
    Morador createMorador(Morador morador);
    Collection<Morador> findAll();
}
