package com.estoque.siste_estoque.controller;

import com.estoque.siste_estoque.service.MovimentoService;
import com.estoque.siste_estoque.service.ComponenteService;
import com.estoque.siste_estoque.model.Componente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String telaMovimentacao(Model model) {
        // Lista ordenada alfabeticamente usando Bubble Sort
        model.addAttribute("componentes", componenteService.listarOrdenado());
        return "movimentar";
    }

    @PostMapping("/movimentar")
    public String movimentar(@RequestParam Long idComponente,
                           @RequestParam String tipo,
                           @RequestParam Integer quantidade,
                           @RequestParam String dataMov,
                           RedirectAttributes redirectAttributes) {
        
        try {
            // Converter data
            LocalDate dataMovimentacao = LocalDate.parse(dataMov);
            
            // Registrar movimentação
            movimentoService.registrarMovimentacao(idComponente, tipo, quantidade, dataMovimentacao);
            
            redirectAttributes.addFlashAttribute("sucesso", 
                "Movimentação registrada com sucesso!");
                
        } catch (RuntimeException e) {
            // Capturar alerta de estoque mínimo
            if (e.getMessage().contains("ALERTA")) {
                redirectAttributes.addFlashAttribute("alerta", e.getMessage());
            } else {
                redirectAttributes.addFlashAttribute("erro", e.getMessage());
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", 
                "Erro ao registrar movimentação: " + e.getMessage());
        }
        
        return "redirect:/estoque";
    }

    @GetMapping("/historico")
    public String historico(Model model) {
        model.addAttribute("movimentos", movimentoService.listarTodos());
        return "historico";
    }
}