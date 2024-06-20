//Simulação de Entrevistas Ada
package br.com.ada.estacionamento.carros;

import br.com.ada.estacionamento.vagas.Vaga;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Carro {

    @Id
    private String placa;

    private String marca;

    private String modelo;

    @OneToOne(cascade = CascadeType.ALL)
    private Vaga vaga;

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
