package io.git.devfranca.access_gate_api.adpater.repository;

import io.git.devfranca.access_gate_api.adpater.entities.MoradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<MoradorEntity, Long> {
    MoradorEntity findByCpf(String cpf);
}