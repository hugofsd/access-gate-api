package io.git.devfranca.access_gate_api.core.ports;

import io.git.devfranca.access_gate_api.core.domain.Usuario;

public interface UsuarioRepositoryPort {

   public Usuario create(Usuario usuario);

   public Usuario obtainByEmail(String email);
}
