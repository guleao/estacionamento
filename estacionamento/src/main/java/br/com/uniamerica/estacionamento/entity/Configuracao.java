package br.com.uniamerica.estacionamento.entity;

// *Import List:
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity // Está declarando que a classe criada abaixo é uma entidade.
@Table (name = "configuracao", schema = "public") // Está criando uma tabela chamada configuração.

// Classe pública chamada configuração ''filho'' de AbstractEntity
public class Configuracao extends AbstractEntity {

    // Criando Coluna valorHora.

    @Getter @Setter
    @Column (name = "valor, hora")
    private BigDecimal valorHora;

    // Criando coluna valorMinutoMulta.

    @Getter @Setter
    @Column (name = "valo, minuto, hora")
    private BigDecimal valorMinutoMulta;

    // Criando coluna inicioExpediente.

    @Getter @Setter
    @Column (name = "inicio, expediente")
    private LocalTime inicioExpediente;

    // Criando coluna fimExpediente.

    @Getter @Setter
    @Column (name = "fim, expediente")
    private LocalTime fimExpediente;

    // Criando coluna tempoParaDesconto.

    @Getter @Setter
    @Column (name = "tempo, para, desconto")
    private LocalTime tempoParaDesconto;

    // Criando coluna tempoDeDesconto.

    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalTime tempoDeDesconto;

    // Criando coluna gerarDesconto.

    @Getter @Setter
    @Column (name = "gerar, desconto")
    private boolean gerarDesconto;
}
