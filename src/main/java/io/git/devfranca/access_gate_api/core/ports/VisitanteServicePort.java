package io.git.devfranca.access_gate_api.core.ports;

import io.git.devfranca.access_gate_api.core.domain.Visitante;

import java.util.Collection;

public interface VisitanteServicePort {
    Visitante createVisitante(Visitante visitante);

    Visitante obtainByRg(String rg);

    Collection<Visitante> listAll();
}
