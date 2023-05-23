package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRep;
    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> findByIDPath (@PathVariable("id") final Long id) {
        final Movimentacao movimentacao = this.movimentacaoRep.findById(id).orElse(null);
        return ResponseEntity.ok(movimentacao);
    }

    // http://localhost:8080/api/movimentacao/1

    // http://localhost:8080/api/movimentacao?id=1

    // Mapeamento da lista, para quando ser solicitada pelo usuario no Post, ser entregue pelo /lista.
    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.movimentacaoRep.findAll());

    }

    // Mapeamento do cadastro de uma nova movimentação, com o método Post que irá enviar informações ao sistema.
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Movimentacao movimentacao) {
        try {
            this.movimentacaoRep.save(movimentacao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    // Mapeamento da edição de uma movimentação usando o metodo Put.
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Movimentacao movimentacao) {
        try {
            final Movimentacao movimentacao1 = this.movimentacaoRep.findById(id).orElse(null);

            if (movimentacao1 == null || !movimentacao1.getId().equals(movimentacao.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.movimentacaoRep.save(movimentacao);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping ("delete/{id}")

    public void deletarMovimentacao (@PathVariable Long id)
    {
        movimentacaoRep.deleteById(id);
    }

}
