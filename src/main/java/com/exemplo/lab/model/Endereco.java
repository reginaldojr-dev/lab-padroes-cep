package com.exemplo.lab.model;

import jakarta.persistence.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cep;

    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco() {}

    public Endereco(String cep, String logradouro, String bairro, String cidade, String uf) {
        this.cep = cep; this.logradouro = logradouro; this.bairro = bairro; this.cidade = cidade; this.uf = uf;
    }

    public Long getId() { return id; }
    public String getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getUf() { return uf; }

    public void setId(Long id) { this.id = id; }
    public void setCep(String cep) { this.cep = cep; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setUf(String uf) { this.uf = uf; }
}
