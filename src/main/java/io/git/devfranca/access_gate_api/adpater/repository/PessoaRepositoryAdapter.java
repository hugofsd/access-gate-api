package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.PessoaEntity;
import io.git.devfranca.access_gate_api.core.domain.Pessoa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaRepositoryAdapter {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    public PessoaEntity createPessoa(Pessoa pessoa) {
        PessoaEntity pessoaEntity = modelMapper.map(pessoa, PessoaEntity.class);
        return pessoaRepository.save(pessoaEntity);
    }
}
