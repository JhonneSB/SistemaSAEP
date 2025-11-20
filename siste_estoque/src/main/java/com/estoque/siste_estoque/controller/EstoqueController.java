/*package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.service.MovimentoService;
import com.estoque.siste_estoque.service.ComponenteService;
import com.estoque.siste_estoque.model.Componente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    private final MovimentoService movimentoService;
    private final ComponenteService componenteService;

    public EstoqueController(MovimentoService movimentoService,
                             ComponenteService componenteService) {
        this.movimentoService = movimentoService;
        this.componenteService = componenteService;
    }

    @GetMapping
    public String tela(Model model) {
        model.addAttribute("componentes", componenteService.listar());
        return "estoque/movimentar";
    }

    @PostMapping("/entrada")
    public String entrada(@RequestParam Long idComponente,
                          @RequestParam Integer quantidade) {

        movimentoService.registrarEntrada(idComponente, quantidade);
        return "redirect:/estoque";
    }

    @PostMapping("/saida")
    public String saida(@RequestParam Long idComponente,
                        @RequestParam Integer quantidade) {

        movimentoService.registrarSaida(idComponente, quantidade);
        return "redirect:/estoque";
    }
}
*/