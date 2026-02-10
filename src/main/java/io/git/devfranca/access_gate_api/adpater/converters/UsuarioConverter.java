package io.git.devfranca.access_gate_api.adpater.converters;

import io.git.devfranca.access_gate_api.adpater.dto.UsuarioDto;
import io.git.devfranca.access_gate_api.core.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    public Usuario toTomain(UsuarioDto usuarioDto){
        return new Usuario(usuarioDto.getId(),usuarioDto.getNome(),usuarioDto.getEmail(),
                usuarioDto.getSenha(), usuarioDto.getAdministrador() );
    }

    public  UsuarioDto toDto (Usuario usuario){
        return  new UsuarioDto(usuario.getId(), usuario.getNome(), usuario.getEmail(),usuario.getSenha(), usuario.getAdministrador());
    }
}
