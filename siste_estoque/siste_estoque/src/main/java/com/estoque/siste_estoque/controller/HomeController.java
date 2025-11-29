package com.estoque.siste_estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estoque.siste_estoque.model.Usuario;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Recupera o usuário da sessão
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        // Verifica se o usuário está logado
        if (usuario == null) {
            return "redirect:/login";
        }

        // Adiciona o usuário no Model para a view
        model.addAttribute("usuario", usuario);
        return "home";
    }

@GetMapping("/logout")
public String logout(HttpSession session, HttpServletResponse response) {
    // Headers adicionais no logout
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    
    session.removeAttribute("usuarioLogado");
    session.invalidate();
    return "redirect:/login";
}
}

