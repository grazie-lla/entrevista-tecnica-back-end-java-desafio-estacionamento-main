package br.com.ada.estacionamento.vagas;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    public VagaRepository repository;

    @PostConstruct
    public void criarVagas(){
        for (int i = 0; i < 10; i++){
            Vaga vaga = new Vaga();
            vaga.setOcupada(false);
            repository.save(vaga);
        }
    }

    public List<Vaga> buscarVagasDisponiveis(){
        return repository.findByOcupadaFalse();
    }

    public Vaga ocuparVaga() throws IllegalAccessException {
        List<Vaga> vagasDisponiveis  = buscarVagasDisponiveis();
        if (!vagasDisponiveis.isEmpty()) {
            Vaga vagaEscolhida = vagasDisponiveis.get(0);
            vagaEscolhida.setOcupada(true);
            return repository.save(vagaEscolhida);
        } else {
            throw new IllegalAccessException("Não há vagas disponiveis.");
        }
    }

}
