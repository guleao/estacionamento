package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Veículo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRep;

    @Autowired
    private VeiculoService veiculoService;
    @GetMapping("/{id}")
    public ResponseEntity<Veículo> findByIDPath (@PathVariable("id") final Long id) {
        final Veículo veiculo = this.veiculoRep.findById(id).orElse(null);
        return ResponseEntity.ok(veiculo);
    }

    // http://localhost:8080/api/veiculo/1

    // http://localhost:8080/api/veiculo?id=1


    // Mapeamento da lista, para quando ser solicitada pelo usuario no Post, ser entregue pelo /lista.
    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.veiculoRep.findAll());

    }

    // Mapeamento do cadastro de um novo veiculo, com o método Post que irá enviar informações ao sistema.
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Veículo veiculo) {
        try {
            veiculoService.validaVeiculo(veiculo);
            return ResponseEntity.ok("Veículo cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    // Mapeamento da edição de um veiculo usando o metodo Put.
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Veículo veiculo) {
        try {
            veiculoService.validaVeiculo(veiculo);

            final Veículo veiculo1 = this.veiculoRep.findById(id).orElse(null);
            if (veiculo1 == null || !veiculo1.getId().equals(veiculo.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }
            this.veiculoRep.save(veiculo);
            return ResponseEntity.ok("Veículo Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // Mapeamento para premoção de um dos ids criados usando o metodo delete.
    @DeleteMapping ("delete/{id}")
    public void deletarVeiculo (@PathVariable Long id)
    {
        veiculoRep.deleteById(id);
    }

}
