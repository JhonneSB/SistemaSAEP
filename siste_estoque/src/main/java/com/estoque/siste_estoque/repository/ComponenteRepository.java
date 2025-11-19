package com.estoque.siste_estoque.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estoque.siste_estoque.model.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    List<Componente> findByNomeContainingIgnoreCase(String nome);
}

