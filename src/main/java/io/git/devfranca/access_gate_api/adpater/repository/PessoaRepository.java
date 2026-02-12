package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
}
