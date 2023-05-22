package br.com.uniamerica.estacionamento.entity;

// *Import List:
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity // Está indicando que a classe criada abaixo é uma entidade.
@Table (name = "marcas", schema = "public") // Está criando uma tabela chamada marcas.

// Classe pública chamada marca ''filho'' de AbstractEntity
public class Marca extends AbstractEntity {

    // Criando coluna nome, para ser usado em marca.

    @Getter @Setter
    @Column (name = "nome_marca" , length = 50, nullable = false)
    private String nome;
}
