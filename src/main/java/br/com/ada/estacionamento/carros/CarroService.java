package br.com.ada.estacionamento.carros;

import br.com.ada.estacionamento.vagas.Vaga;
import br.com.ada.estacionamento.vagas.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {


    public CarroRepository carroRepository;
    public VagaService vagaService;

    public CarroService(CarroRepository carroRepository, VagaService vagaService) {
        this.carroRepository = carroRepository;
        this.vagaService = vagaService;
    }

    public void cadastrarCarro(Carro carro) throws IllegalAccessException {
        List<Vaga> vagasDispoiveis = vagaService.buscarVagasDisponiveis();
        if(!vagasDispoiveis.isEmpty()){
            carroRepository.save(carro);
        } else {
            throw new IllegalAccessException ("Estacionamento loado, não é possivel cadastrar");
        }
    }

    public void estacionar(Carro carro) throws IllegalAccessException {
        Vaga vagasDisponivel =  vagaService.ocuparVaga();
        if(vagasDisponivel != null){
            carro.setVaga(vagasDisponivel);
            carroRepository.save(carro);
        } else{
            throw new IllegalAccessException("Não há vagas");
        }
    }

    public Carro retirarCarro(String placa){
        Optional<Carro> byId = carroRepository.findById(placa);
        if (byId.isPresent()){
            Carro carro = byId.get();
            if (carro.getVaga() != null) {
                carro.getVaga().setOcupada(false);
                carro.setVaga(null);
                return carroRepository.save(carro);
            } else {
                throw new IllegalArgumentException("Carro já está sem vaga.");
            }
        } else {
            throw new IllegalArgumentException("Carro não encontrado.");
        }
    }
}
