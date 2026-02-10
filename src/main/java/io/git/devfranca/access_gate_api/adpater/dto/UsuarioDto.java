package io.git.devfranca.access_gate_api.adpater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // gatters e setters, equals  etc...
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean administrador;


}
