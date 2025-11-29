package com.estoque.siste_estoque.repository;

import com.estoque.siste_estoque.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimento, Long> {
    boolean existsByComponenteId(Long componenteId);
}