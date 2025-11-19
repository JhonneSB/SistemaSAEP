package com.estoque.siste_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estoque.siste_estoque.model.Movimento;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
}
