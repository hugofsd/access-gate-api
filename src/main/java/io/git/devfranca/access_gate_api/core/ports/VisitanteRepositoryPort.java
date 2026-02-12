package io.git.devfranca.access_gate_api.core.ports;

import io.git.devfranca.access_gate_api.core.domain.Visitante;

import java.util.Collection;
import java.util.Optional;

public interface VisitanteRepositoryPort {

    public Visitante create(Visitante visitante);

    public Optional<Visitante> obtainByRg(String rg);

    Collection<Visitante> listAll();
}

