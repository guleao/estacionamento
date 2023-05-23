package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/api/marca")
public class MarcaController {

    @Autowired // Permite a utilização dos metodos de determinada classe, sem precisar instanciala, fazendo a injeçaõ de dependência
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;
    @GetMapping("/{id}")
        public ResponseEntity<Marca> findByIDPath (@PathVariable("id") final Long id) {
            final Marca marca = this.marcaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(marca);
        }

        // http://localhost:8080/api/marca/1

        // http://localhost:8080/api/marca?id=1

        // Mapeamento da lista, para quando ser solicitada pelo usuario no Post, ser entregue pelo /lista.
        @GetMapping("/lista")
        public ResponseEntity<?> ListaCompleta() {
            return ResponseEntity.ok(this.marcaRepository.findAll());

        }

        // Mapeamento do cadastro de um nova marca, com o método Post que irá enviar informações ao sistema.
        @PostMapping
        public ResponseEntity<?> cadastrar (@RequestBody final Marca marca) {
            try {
                marcaService.validaMarca(marca);
                return ResponseEntity.ok("Registro cadastrado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
            }
        }

    // Mapeamento da edição de uma marca usando o metodo Put.
        @PutMapping
        public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Marca marca) {
            try {

                final Marca marca1 = this.marcaRepository.findById(id).orElse(null);

                if (marca1 == null || !marca1.getId().equals(marca.getId())) {
                    throw new RuntimeException("Nao foi possivel indentificar o registro informado");
                }
                this.marcaRepository.save(marca);
                return ResponseEntity.ok("Registro Cadastrado com Sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError()
                        .body("Error: " + e.getCause().getCause().getMessage());
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }

    @DeleteMapping ("delete/{id}")

        public void deletarMarca (@PathVariable Long id)
        {
            marcaRepository.deleteById(id);
        }

    }



