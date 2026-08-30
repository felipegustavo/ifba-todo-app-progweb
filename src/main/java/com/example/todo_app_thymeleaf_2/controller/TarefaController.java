package com.example.todo_app_thymeleaf_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo_app_thymeleaf_2.TarefaService;
import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @GetMapping({"", "/listar"})
    public String listar(Model model) {
        model.addAttribute("tarefa", new TarefaDTO());
        carregarTarefas(model);
        return "tarefas";
    }
    
    @PostMapping("/salvar")
    public String postMethodName(@Valid @ModelAttribute("tarefa") TarefaDTO tarefa,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            carregarTarefas(model);
            return "tarefas";
        }

        service.criarTarefa(tarefa);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        return "redirect:/tarefas/listar";
    }
    

    private void carregarTarefas(Model model) {
        var tarefas = service.buscarTarefas();
        model.addAttribute("tarefas", tarefas);
    }

}
