package br.com.uniamerica.estacionamento.entity;
// *Import List:
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Está declarando que a classe criada abaixo é uma entidade.
@Table (name = "modelo", schema = "public") // Está criando uma tabela chamada modelo.

// Classe pública chamada modelo ''filho'' de AbstractEntity.
public class Modelo extends  AbstractEntity {

    // Criando coluna nome.

    @Getter @Setter
    @Column (name = "nome", length = 50, nullable = false)
    private String nome;

    // Criando coluna marca.

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Marca marca;

}
