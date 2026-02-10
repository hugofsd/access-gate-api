package io.git.devfranca.access_gate_api.infra;

import io.git.devfranca.access_gate_api.core.ports.UsuarioServicePort;
import io.git.devfranca.access_gate_api.core.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public UsuarioServicePort usuarioServiceImpl(){
    return new UsuarioService();
    }

}
