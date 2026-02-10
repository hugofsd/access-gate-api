package io.git.devfranca.access_gate_api.adpater.controller;

import io.git.devfranca.access_gate_api.adpater.converters.UsuarioConverter;
import io.git.devfranca.access_gate_api.adpater.dto.UsuarioDto;
import io.git.devfranca.access_gate_api.core.ports.UsuarioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor // criar contrutor de injeção
public class UsuarioController {

    private final UsuarioServicePort usuarioServicoPort;

    private final UsuarioConverter usuarioConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto create(@RequestBody UsuarioDto usuarioDto) {
      return usuarioConverter.toDto(usuarioServicoPort.createUsuario(usuarioConverter.toTomain(usuarioDto)));
    }


}
