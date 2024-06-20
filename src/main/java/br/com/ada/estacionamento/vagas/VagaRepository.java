package br.com.ada.estacionamento.vagas;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, Integer> {
    List<Vaga> findByOcupadaFalse();
}
