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
        System.out.println("=== EDITANDO COMPONENTE ===");
        System.out.println("ID do componente carregado: " + componente.getId());
        System.out.println("Nome do componente carregado: " + componente.getNome());
        System.out.println("===========================");
        
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
        
        System.out.println("=== DADOS RECEBIDOS NO CONTROLLER ===");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Lote: " + lote);
        System.out.println("Tensão: " + tensao);
        System.out.println("Datasheet: " + datasheet);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Mínimo: " + minimo);
        System.out.println("=====================================");
        
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

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        componenteService.excluir(id);
        return "redirect:/componentes";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String termo, Model model) {
        List<Componente> lista = componenteService.buscarPorNome(termo);
        model.addAttribute("componentes", lista);
        return "componentes";
    }

    @GetMapping("/movimentar")
public String movimentar(@RequestParam Long componenteId, Model model) {
    Componente componente = componenteService.obter(componenteId);
    if (componente == null) {
        return "redirect:/componentes";
    }
    model.addAttribute("componente", componente);
    return "movimentar-componente";
}


}