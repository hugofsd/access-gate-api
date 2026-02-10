package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.UsuarioEntity;
import io.git.devfranca.access_gate_api.core.domain.Usuario;
import io.git.devfranca.access_gate_api.core.ports.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Override
    public Usuario create(Usuario usuario) {
        UsuarioEntity novoUsuario = usuarioRepository
                .save(modelMapper.map(usuario, UsuarioEntity.class)); // converter obj domain em entidade
        return modelMapper.map(novoUsuario, Usuario.class);
    }
}
