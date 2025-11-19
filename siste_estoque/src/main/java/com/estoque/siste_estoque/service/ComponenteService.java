package com.estoque.siste_estoque.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.estoque.siste_estoque.model.Componente;
import com.estoque.siste_estoque.repository.ComponenteRepository;

@Service
public class ComponenteService {

    private final ComponenteRepository repo;

    public ComponenteService(ComponenteRepository repo) {
        this.repo = repo;
    }

    public Componente salvar(Componente c) {
        return repo.save(c);
    }

    public List<Componente> listar() {
        return repo.findAll();
    }

    public List<Componente> buscarPorNome(String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
    }

    public Componente obter(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public void atualizarQuantidade(Componente c, Integer novaQtd) {
        c.setQuantidade(novaQtd);
        repo.save(c);
    }
}
