package com.estoque.siste_estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estoque.siste_estoque.model.Movimento;
import com.estoque.siste_estoque.model.Componente;
import com.estoque.siste_estoque.repository.MovimentoRepository;
import com.estoque.siste_estoque.repository.ComponenteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentoService {
    
    @Autowired
    private MovimentoRepository movimentoRepository;
    
    @Autowired
    private ComponenteRepository componenteRepository;
    
    public void registrarMovimentacao(Long idComponente, String tipo, Integer quantidade, LocalDate dataMov) {
        Componente componente = componenteRepository.findById(idComponente)
            .orElseThrow(() -> new RuntimeException("Componente não encontrado"));
        
        // Atualizar estoque
        if ("entrada".equalsIgnoreCase(tipo)) {
            componente.setQuantidade(componente.getQuantidade() + quantidade);
        } else if ("saida".equalsIgnoreCase(tipo)) {
            // Verificar estoque mínimo ANTES da movimentação
            verificarEstoqueMinimo(componente, quantidade);
            componente.setQuantidade(componente.getQuantidade() - quantidade);
        }
        
        // Salvar componente atualizado
        componenteRepository.save(componente);
        
        // Registrar movimento
        Movimento movimento = new Movimento();
        movimento.setComponente(componente);
        movimento.setTipo(tipo);
        movimento.setQuantidade(quantidade);
        movimento.setDataMov(dataMov != null ? dataMov : LocalDate.now());
        
        movimentoRepository.save(movimento);
    }
    
    private void verificarEstoqueMinimo(Componente componente, Integer quantidadeSaida) {
        int estoqueFuturo = componente.getQuantidade() - quantidadeSaida;
        if (componente.getMinimo() != null && estoqueFuturo < componente.getMinimo()) {
            throw new RuntimeException(
                "ALERTA: Estoque abaixo do mínimo! " +
                "Componente: " + componente.getNome() + 
                " | Estoque futuro: " + estoqueFuturo + 
                " | Mínimo: " + componente.getMinimo()
            );
        }
    }
    
    public List<Movimento> listarTodos() {
        return movimentoRepository.findAll();
    }
}