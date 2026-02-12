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
        Usuario usuarioExistente = usuarioRepositoryPort.obtainByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            throw new IllegalArgumentException("Usuário já existe!");
        }
        return usuarioRepositoryPort.create(usuario);
    }


}
