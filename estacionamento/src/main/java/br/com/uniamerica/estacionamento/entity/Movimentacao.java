package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity // Está declarando que essa classe é uma entidade.
@Table (name = "movimentacao", schema = "public") // Criação da tabela movimentacao

public class Movimentacao  extends AbstractEntity{

    // Criação da coluna veiculo.

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo", nullable = false, unique = true)
    private Veículo veiculo;

    // Criação da coluna condutor.

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "condutor", nullable = false)
    private Condutor condutor;

    // Criação da coluna entrada.

    @Getter @Setter
    @Column (name = "entrada", nullable = false, length = 20)
    private LocalDateTime entrada;

    // Criação da coluna saida.

    @Getter @Setter
    @Column (name = "saida")
    private LocalDateTime saida;

    // Criação da coluna tempo.

    @Getter @Setter
    @Column (name = "tempo")
    private LocalDateTime tempo;

    // Criação da coluna tempoDesconto.

    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalDateTime tempoDesconto;

    // Criação da coluna tempoMulta.

    @Getter @Setter
    @Column (name = "tempo, multa")
    private LocalDateTime tempoMulta;

    // Criação da coluna valorDesconto.

    @Getter @Setter
    @Column (name = "valor, desconto")
    private BigDecimal valorDesconto;

    // Criação da coluna valorMulta.

    @Getter @Setter
    @Column (name = "valor, multa")
    private BigDecimal valorMulta;

    // Criação da coluna valorTotal.
    @Getter @Setter
    @Column (name = "valor, total")
    private BigDecimal valorTotal;

    // Criação da coluna valorHora.

    @Getter @Setter
    @Column (name = "valor, hora")
    private BigDecimal valorHora;

    // Criação da coluna valorHoraMulta.

    @Getter @Setter
    @Column (name = "valor, hora, multa")
    private BigDecimal valorHoraMulta;

}
