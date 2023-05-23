package br.com.uniamerica.estacionamento.controller;


import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/condutor")
public class CondutorController {
    @Autowired // Permite a utilização dos metodos de determinada classe, sem precisar instanciala, fazendo a injeçaõ de dependência
    private CondutorRepository condutorRepository;

    @Autowired // Permite a utilização dos metodos de determinada classe, sem precisar instanciala, fazendo a injeçaõ de dependência
    private CondutorService condutorServ;

    @GetMapping("/{id}") // Mapeia a classe abaixo
    public ResponseEntity<Condutor> findByIDPath (@PathVariable("id") final Long id) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return ResponseEntity.ok(condutor);
    }

    // http://localhost:8080/api/condutor/1

    // http://localhost:8080/api/condutor?id=1

    // Mapeamento da lista, para quando ser solicitada pelo usuario no Post, ser entregue pelo /lista.
    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.condutorRepository.findAll());

    }

    // Mapeamento do cadastro de um novo condutor., com o método Post que irá enviar informações ao sistema.
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Condutor condutor) {
        try {
           condutorServ.validaCondutor(condutor);
            return ResponseEntity.ok("Condutor cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    // Mapeamento da edição de um condutor usando o metodo Put.
    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Condutor condutor) {
        try {
            condutorServ.atualizaCondutor(condutor);
            final Condutor condutor1 = this.condutorRepository.findById(id).orElse(null);
            if (condutor1 == null || !condutor1.getId().equals(condutor.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }
            this.condutorRepository.save(condutor);
            return ResponseEntity.ok("Condutor atualizado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping ("delete/{id}")

    public void deletarCondutor (@PathVariable Long id)
    {
        condutorRepository.deleteById(id);
    }

}

