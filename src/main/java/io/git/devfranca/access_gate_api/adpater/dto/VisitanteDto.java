package io.git.devfranca.access_gate_api.adpater.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisitanteDto {
    private Long id;
    private String nome;
    private String rg;
}