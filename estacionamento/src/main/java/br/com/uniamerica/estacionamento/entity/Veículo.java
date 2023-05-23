package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "veiculos", schema = "public")
public class Veículo extends AbstractEntity {

    // Criação da coluna placa.

    @Getter @Setter
    @Column (name = "placa", length = 8, nullable = false, unique = true)
    private String placa;

    // Criação da coluna modelo.

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Modelo modelo;

    // Criação da coluna ano.

    @Getter @Setter
    @Column (name = "ano" , nullable = false)
    private int ano;

    // Criação da coluna cor.

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", length = 20, nullable = false)
    private Cor cor;

    // Criação da coluna tipo.

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column (name = "tipo" , length = 20, nullable = false)
    private Tipo tipo;
}
