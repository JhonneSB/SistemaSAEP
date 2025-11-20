/*/package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.service.UsuarioService;
import com.estoque.siste_estoque.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
                        Model model) {

        Usuario usuario = usuarioService.validarLogin(login, senha);

        if (usuario == null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }

        model.addAttribute("usuario", usuario);
        return "redirect:/principal";
    }
}
*/
