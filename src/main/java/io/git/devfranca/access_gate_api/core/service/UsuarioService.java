package io.git.devfranca.access_gate_api.core.service;

import io.git.devfranca.access_gate_api.core.domain.Usuario;
import io.git.devfranca.access_gate_api.core.ports.UsuarioServicePort;

public class UsuarioService implements UsuarioServicePort {

    @Override
    public Usuario createUsuario(Usuario usuario){
        return usuario;
    }
}
