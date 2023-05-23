package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/configuracao") // Mapear a classe toda, mostrando como chamar e tudo abaixo está dentro
// do URI.
public class ConfiguracaoController {
    @Autowired
    private ConfiguracaoRepository configRep;
    @GetMapping("/{id}")
    public ResponseEntity<Configuracao> findByIDPath (@PathVariable("id") final Long id) {
        final Configuracao configuracao = this.configRep.findById(id).orElse(null);
        return ResponseEntity.ok(configuracao);
    }

    // http://localhost:8080/api/configuracao/1

    // http://localhost:8080/api/configuracao?id=1

    // Mapeamento da lista, para quando ser solicitada pelo usuario no Post, ser entregue pelo /lista.
    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.configRep.findAll());

    }

    // Mapeamento do cadastro de uma nova configuracao, com o método Post que irá enviar informações ao sistema.
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Configuracao configuracao) {
        try {
            this.configRep.save(configuracao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    // Mapeamento da edição de uma configuração usando o metodo Put.
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Configuracao configuracao) {
        try {
            final Configuracao configuracao1 = this.configRep.findById(id).orElse(null);

            if (configuracao1 == null || !configuracao1.getId().equals(configuracao.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.configRep.save(configuracao);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping ("delete/{id}")

    public void deletarConfiguracao (@PathVariable Long id)
    {
        configRep.deleteById(id);
    }

}


