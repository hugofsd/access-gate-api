package io.git.devfranca.access_gate_api.adpater.controller;

import io.git.devfranca.access_gate_api.adpater.converters.MoradorConverter;
import io.git.devfranca.access_gate_api.adpater.dto.MoradorDto;
import io.git.devfranca.access_gate_api.core.domain.Morador;
import io.git.devfranca.access_gate_api.core.ports.MoradorServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/moradores")
@RequiredArgsConstructor // criar contrutor de injeção
public class MoradorController {

    private final MoradorServicePort moradorServicePort;

    private final MoradorConverter moradorConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MoradorDto create(@RequestBody MoradorDto moradorDto) {
        Morador novoMorador = moradorServicePort.createMorador(moradorConverter.toTomain(moradorDto));
        return moradorConverter.toDto(novoMorador);
    }

    @GetMapping
    public Collection<MoradorDto> findAll() {
        return moradorServicePort.findAll()
                .stream()
                .map(moradorConverter::toDto)
                .toList();
    }


}
