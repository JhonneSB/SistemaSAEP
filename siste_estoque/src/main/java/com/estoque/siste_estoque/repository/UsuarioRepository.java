package com.estoque.siste_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estoque.siste_estoque.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLoginAndSenha(String login, String senha);
}

