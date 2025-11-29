package com.estoque.siste_estoque.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Componente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String datasheet;
    private String lote;
    private String tensao;

    private Integer quantidade;
    private Integer minimo;

    // CORREÇÃO: Adicionar cascade para exclusão em cascata
    @OneToMany(mappedBy = "componente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimento> movimentos;

    // GETTERS E SETTERS COMPLETOS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDatasheet() { return datasheet; }
    public void setDatasheet(String datasheet) { this.datasheet = datasheet; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public String getTensao() { return tensao; }
    public void setTensao(String tensao) { this.tensao = tensao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Integer getMinimo() { return minimo; }
    public void setMinimo(Integer minimo) { this.minimo = minimo; }

    public List<Movimento> getMovimentos() { return movimentos; }
    public void setMovimentos(List<Movimento> movimentos) { this.movimentos = movimentos; }
}