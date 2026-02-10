package io.git.devfranca.access_gate_api.core.service;

import io.git.devfranca.access_gate_api.core.domain.Usuario;
import io.git.devfranca.access_gate_api.core.ports.UsuarioRepositoryPort;
import io.git.devfranca.access_gate_api.core.ports.UsuarioServicePort;

public class UsuarioService implements UsuarioServicePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario createUsuario(Usuario usuario){
        return usuarioRepositoryPort.create(usuario);
    }
}
