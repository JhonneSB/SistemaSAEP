package com.estoque.siste_estoque.service;

import org.springframework.stereotype.Service;
import com.estoque.siste_estoque.repository.UsuarioRepository;
import com.estoque.siste_estoque.model.Usuario;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario login(String login, String senha) {
        return repo.findByLoginAndSenha(login, senha);
    }
}
