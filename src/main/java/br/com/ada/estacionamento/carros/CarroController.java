package br.com.ada.estacionamento.carros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class CarroController {

    public final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrarCarro(@RequestBody Carro carro){
        try {
            service.cadastrarCarro(carro);
            return ResponseEntity.created(null).body(carro);
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/estacionar")
    public ResponseEntity<Carro> estacionar(@RequestBody Carro carro){
        try{
            service.estacionar(carro);
            return ResponseEntity.ok(carro);
        }catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{placa}")
    public ResponseEntity<Carro> retirarCarro(@PathVariable String placa){
        try {
            Carro carroAtualizado = service.retirarCarro(placa);
            if (carroAtualizado != null) {
                return ResponseEntity.ok(carroAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
