package io.git.devfranca.access_gate_api.adpater.converters;

import io.git.devfranca.access_gate_api.adpater.dto.MoradorDto;
import io.git.devfranca.access_gate_api.core.domain.Morador;
import io.git.devfranca.access_gate_api.core.domain.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class MoradorConverter {

    public Morador toTomain(MoradorDto moradorDto) {
        return new Morador(moradorDto.getId(),
                moradorDto.getCpf(),
                moradorDto.getEndereco(),
                moradorDto.getCelular(),
                new Pessoa(null, moradorDto.getNome())
        );
    }

    public MoradorDto toDto (Morador morador) {
        return new MoradorDto(morador.getId(),
                morador.getPessoa().getNome(),
                morador.getCpf(),
                morador.getEndereco(),
                morador.getCelular());
    }

}
