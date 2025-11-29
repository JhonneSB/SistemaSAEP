package com.estoque.siste_estoque.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estoque.siste_estoque.model.Componente;
import com.estoque.siste_estoque.repository.ComponenteRepository;

@Service
public class ComponenteService {
    
    @Autowired
    private ComponenteRepository componenteRepository;
    
    // Listar ordenado por nome (usando algoritmo de ordenação)
    public List<Componente> listarOrdenado() {
        List<Componente> componentes = componenteRepository.findAll();
        
        // Algoritmo de ordenação: Bubble Sort
        bubbleSortPorNome(componentes);
        
        return componentes;
    }
    
    // Algoritmo Bubble Sort para ordenar por nome
    private void bubbleSortPorNome(List<Componente> componentes) {
        int n = componentes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String nome1 = componentes.get(j).getNome().toLowerCase();
                String nome2 = componentes.get(j + 1).getNome().toLowerCase();
                
                if (nome1.compareTo(nome2) > 0) {
                    // Troca os elementos
                    Componente temp = componentes.get(j);
                    componentes.set(j, componentes.get(j + 1));
                    componentes.set(j + 1, temp);
                }
            }
        }
    }
    
    // Método alternativo usando Quick Sort (mais eficiente)
    public List<Componente> listarOrdenadoQuickSort() {
        List<Componente> componentes = componenteRepository.findAll();
        quickSortPorNome(componentes, 0, componentes.size() - 1);
        return componentes;
    }
    
    // Algoritmo Quick Sort para ordenar por nome
    private void quickSortPorNome(List<Componente> componentes, int low, int high) {
        if (low < high) {
            int pi = partition(componentes, low, high);
            quickSortPorNome(componentes, low, pi - 1);
            quickSortPorNome(componentes, pi + 1, high);
        }
    }
    
    private int partition(List<Componente> componentes, int low, int high) {
        String pivot = componentes.get(high).getNome().toLowerCase();
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (componentes.get(j).getNome().toLowerCase().compareTo(pivot) <= 0) {
                i++;
                Componente temp = componentes.get(i);
                componentes.set(i, componentes.get(j));
                componentes.set(j, temp);
            }
        }
        
        Componente temp = componentes.get(i + 1);
        componentes.set(i + 1, componentes.get(high));
        componentes.set(high, temp);
        
        return i + 1;
    }
    
    // Métodos existentes (mantidos para compatibilidade)
    public List<Componente> listar() {
        return componenteRepository.findAll();
    }
    
    public Componente obter(Long id) {
        return componenteRepository.findById(id).orElse(null);
    }
    
    public Componente salvar(Componente componente) {
        if (componente.getId() != null) {
            System.out.println("ATUALIZANDO componente ID: " + componente.getId());
        } else {
            System.out.println("CRIANDO novo componente");
        }
        return componenteRepository.save(componente);
    }
    
    public void excluir(Long id) {
        componenteRepository.deleteById(id);
    }
    
    public List<Componente> buscarPorNome(String termo) {
        return componenteRepository.findByNomeContainingIgnoreCase(termo);
    }
}