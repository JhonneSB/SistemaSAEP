package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.model.Componente;
import com.estoque.siste_estoque.service.ComponenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "componentes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("comp", new Componente());
        return "componente-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Componente componente = componenteService.obter(id);
        if (componente == null) {
            return "redirect:/componentes";
        }
        model.addAttribute("comp", componente);
        return "componente-form";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam(required = false) Long id,
            @RequestParam String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String lote,
            @RequestParam(required = false) String tensao,
            @RequestParam(required = false) String datasheet,
            @RequestParam(required = false) Integer quantidade,
            @RequestParam(required = false) Integer minimo) {
        
        Componente componente = new Componente();
        componente.setId(id);
        componente.setNome(nome);
        componente.setTipo(tipo);
        componente.setLote(lote);
        componente.setTensao(tensao);
        componente.setDatasheet(datasheet);
        componente.setQuantidade(quantidade);
        componente.setMinimo(minimo);
        
        componenteService.salvar(componente);
        return "redirect:/componentes";
    }

    // CORREÇÃO: Exclusão com tratamento de erro
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            boolean sucesso = componenteService.excluirComponente(id);
            if (sucesso) {
                redirectAttributes.addFlashAttribute("sucesso", "Componente excluído com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Componente não encontrado!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", 
                "Erro ao excluir componente: " + e.getMessage() + 
                ". O componente pode ter movimentações associadas.");
        }
        return "redirect:/componentes";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String termo, Model model) {
        List<Componente> lista = componenteService.buscarPorNome(termo);
        model.addAttribute("componentes", lista);
        return "componentes";
    }
}