package io.git.devfranca.access_gate_api.adpater.controller;

import io.git.devfranca.access_gate_api.adpater.converters.VisitanteConverter;
import io.git.devfranca.access_gate_api.adpater.dto.VisitanteDto;
import io.git.devfranca.access_gate_api.core.domain.Visitante;
import io.git.devfranca.access_gate_api.core.ports.VisitanteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/visitantes")
@RequiredArgsConstructor
public class VisitanteController {

    private final VisitanteConverter visitanteConverter;
    private final VisitanteServicePort visitanteServicePort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitanteDto create(@RequestBody VisitanteDto visitanteDto) {
        Visitante visitante = visitanteServicePort.createVisitante(visitanteConverter.toTomain(visitanteDto));
        return visitanteConverter.toDto(visitante);
    }

    @GetMapping
    public List<VisitanteDto> listAll() {
        return visitanteServicePort.listAll().stream()
                .map(visitanteConverter::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{rg}")
    public VisitanteDto obtainByRg(@PathVariable String rg) {
        return visitanteConverter.toDto(visitanteServicePort.obtainByRg(rg));
    }

}
