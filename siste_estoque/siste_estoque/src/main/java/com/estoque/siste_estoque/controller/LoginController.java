package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import com.estoque.siste_estoque.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @PostMapping("/login")
    public String logar(@RequestParam String login,
                        @RequestParam String senha,
                        Model model,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        Usuario usuario = usuarioService.login(login, senha);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }

        // 1. Salva na sessão para manter o login
        session.setAttribute("usuarioLogado", usuario);

        
        return "redirect:/home";
    }

}