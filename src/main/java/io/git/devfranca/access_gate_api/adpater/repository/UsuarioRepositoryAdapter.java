package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.PessoaEntity;
import io.git.devfranca.access_gate_api.adpater.entities.UsuarioEntity;
import io.git.devfranca.access_gate_api.core.domain.Pessoa;
import io.git.devfranca.access_gate_api.core.domain.Usuario;
import io.git.devfranca.access_gate_api.core.ports.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PessoaRepositoryAdapter pessoaRepositoryAdapter;

    @Override
    public Usuario create(Usuario usuario) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);
        usuarioEntity.setPessoaEntity(pessoaRepositoryAdapter.createPessoa(usuario.getPessoa()));
        UsuarioEntity novoUsuario = usuarioRepository.save(usuarioEntity);
        return modelMapper.map(novoUsuario, Usuario.class);
    }

    @Override
    public Usuario obtainByEmail(String email) {
        UsuarioEntity usuarioByEmail = usuarioRepository
                .findByEmail(email);
        if (usuarioByEmail == null) {
            return null;
        }
        return modelMapper.map(usuarioByEmail, Usuario.class);
    }
}
