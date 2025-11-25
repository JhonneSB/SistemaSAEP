package com.estoque.siste_estoque.repository;

import com.estoque.siste_estoque.model.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    
    List<Componente> findByNomeContainingIgnoreCase(String nome);
    
    // Ou usando @Query personalizada
    @Query("SELECT c FROM Componente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Componente> buscarPorNome(@Param("termo") String termo);
}