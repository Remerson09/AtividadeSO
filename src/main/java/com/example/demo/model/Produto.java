package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false) //Aceita null no back e no Banco de Bados
    //@NotNull // Não aceita null nem no Back nem no Banco de Dados
    private Long id;

    @NotNull @NotBlank
    private String titulo;
    @NotNull
    private String descricao;

    @NotNull @DecimalMin("0.05")
    private double preco;
    @NotNull
    private int estoque;
    @NotNull
    /** codigo de barras.*/
    private String ean;
}
