package com.estoque.siste_estoque.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Componente componente;

    @ManyToOne
    private Usuario usuario;

    private String tipo;
    private Integer quantidade;
    private LocalDate dataMov;

    public Long getId() { return id; }

    public Componente getComponente() { return componente; }
    public void setComponente(Componente componente) { this.componente = componente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDate getDataMov() { return dataMov; }
    public void setDataMov(LocalDate dataMov) { this.dataMov = dataMov; }
}
