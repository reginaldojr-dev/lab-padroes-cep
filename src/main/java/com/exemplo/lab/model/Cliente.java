package com.exemplo.lab.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne(optional = false)
    private Endereco endereco;

    public Cliente() {}

    public Cliente(String nome, Endereco endereco) {
        this.nome = nome; this.endereco = endereco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Endereco getEndereco() { return endereco; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
}
