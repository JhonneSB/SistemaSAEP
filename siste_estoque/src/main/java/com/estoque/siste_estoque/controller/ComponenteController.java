package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.model.Componente;
import com.estoque.siste_estoque.service.ComponenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/componentes")
public class ComponenteController {

    private final ComponenteService componenteService;

    public ComponenteController(ComponenteService componenteService) {
        this.componenteService = componenteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("componentes", componenteService.listar());
        return "componentes/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("componente", new Componente());
        return "componentes/form";
    }

    @PostMapping
    public String salvar(Componente componente) {
        componenteService.salvar(componente);
        return "redirect:/componentes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("componente", componenteService.obter(id));
        return "componentes/form";
    }

    @PostMapping("/atualizar")
    public String atualizar(Componente componente) {
        componenteService.salvar(componente);
        return "redirect:/componentes";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        componenteService.excluir(id);
        return "redirect:/componentes";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String termo, Model model) {
        List<Componente> lista = componenteService.buscarPorNome(termo);
        model.addAttribute("componentes", lista);
        return "componentes/listar";
    }
}

