package br.com.uniamerica.estacionamento.entity;

// *Import List:
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@MappedSuperclass // Está indicando que será como se fosse a classe pai de uma herança.
public abstract class AbstractEntity {

    // Criação de colunas dentro do banco de dados.
    // Criação da coluna ID.

    @Id // Está indicando
    @Getter // Indica que é um get(Solicitar informação).
    @GeneratedValue(strategy =  GenerationType.AUTO) // Coloco para gerar os valores dos id no banco de dados.
    @Column(name = "id" , nullable = false, unique = true)
    private Long id;

    // Criação da coluna cadastro.

    @Getter @Setter
    @Column (name = "cadastro")
    private LocalDateTime cadastro;

    // Criação da coluna edição.

    @Getter @Setter
    @Column (name = "edicao")
    private LocalDateTime edicao;

    // Criação da coluna ativo.

    @Getter @Setter
    @Column (name = "ativo", nullable = false)
    private boolean ativo;

    @PrePersist // Criado para ser acionado antes que uma entidade não será persistida.
    private void prePersist ()
    {
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }
    @PreUpdate // Criado para ser acionado antes que uma entidade atualize.
    private void preUpdate ()
    {
        this.edicao = LocalDateTime.now();
    }
}
