package io.git.devfranca.access_gate_api.adpater.converters;

import io.git.devfranca.access_gate_api.adpater.dto.VisitanteDto;
import io.git.devfranca.access_gate_api.core.domain.Pessoa;
import io.git.devfranca.access_gate_api.core.domain.Visitante;
import org.springframework.stereotype.Component;

@Component
public class VisitanteConverter {

    public Visitante toTomain(VisitanteDto visitanteDto) {
        return new Visitante(visitanteDto.getId(), visitanteDto.getRg(),
                new Pessoa(null, visitanteDto.getNome())
        );
    }

    public VisitanteDto toDto (Visitante visitante) {
        return new VisitanteDto(visitante.getId(),visitante.getPessoa().getNome(), visitante.getRg());
    }
}
