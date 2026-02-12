package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.VisitanteEntity;
import io.git.devfranca.access_gate_api.core.domain.Visitante;
import io.git.devfranca.access_gate_api.core.ports.VisitanteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VisitanteRepositoryAdapter implements VisitanteRepositoryPort {

    private final VisitanteRepository visitanteRepository;
    private final PessoaRepositoryAdapter pessoaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Visitante create(Visitante visitante) {
        VisitanteEntity entity = modelMapper.map(visitante, VisitanteEntity.class);
        entity.setPessoaEntity(pessoaRepository.createPessoa(visitante.getPessoa()));
        return modelMapper.map(visitanteRepository.save(entity), Visitante.class);
    }

    @Override
    public Optional<Visitante> obtainByRg(String rg) {
        return visitanteRepository.findByRg(rg)
                .map(visitanteEntity -> modelMapper.map(visitanteEntity, Visitante.class));
    }

    @Override
    public Collection<Visitante> listAll() {
        return visitanteRepository.findAll().stream()
                .map(visitanteEntity -> modelMapper.map(visitanteEntity, Visitante.class))
                .toList();
    }
}
