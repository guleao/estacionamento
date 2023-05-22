package br.com.uniamerica.estacionamento.entity;

// *Import List:
import br.com.uniamerica.estacionamento.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Label;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity // Está indicando que a classe criada abaixo é uma entidade.
//@Audited
@Table(name = "condutores", schema = "public") // Está criando uma tabela de nome condutores.
//@AuditTable (value = "condutores_audit", schema = "audit")

// Classe pública chamada Condutor ''filho'' de AbstractEntity
public class Condutor extends AbstractEntity
{

    // Criação da coluna nome.

    @Getter @Setter
    @Column (name = "nome", nullable = false, length = 50)
    private String nome;

    // Criação da coluna cpf.

    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true , length = 15)
    private String cpf;

    // Criação da coluna telefone.

    @Getter @Setter
    @Column (name = "telefone", nullable = false, unique = true, length = 17)
    private String telefone;

    // Criação da coluna tempoPago.

    @Getter @Setter
    @Column (name = "tempo,pago")
    private LocalTime tempoPago;

    // Criação da coluna tempoDesconto.

    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalTime tempoDesconto;
}

