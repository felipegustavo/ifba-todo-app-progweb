package com.example.todo_app_thymeleaf_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo_app_thymeleaf_2.TarefaService;
import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



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
    
    @PostMapping({"/salvar", "/alterar"})
    public String salvar(@Valid @ModelAttribute("tarefa") TarefaDTO tarefa,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            carregarTarefas(model);
            return "tarefas";
        }

        if (tarefa.getId() != null) {
            service.atualizarTarefa(tarefa);
        } else {
            service.criarTarefa(tarefa);
        }

        redirectAttributes.addFlashAttribute("mensagem", "Tarefa criada com sucesso.");
        return "redirect:/tarefas/listar";
    }
    
    @GetMapping("/alterar/{id}")
    public String editar(@PathVariable Long id,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        var tarefa = this.service.buscarTarefa(id);

        if (tarefa == null) {
            redirectAttributes.addFlashAttribute("erro", "Tarefa não existe no banco.");
            return "redirect:/tarefas/listar";
        }

        model.addAttribute("tarefa", tarefa);
        carregarTarefas(model);
        return "tarefas";
    }
    
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id,
                        Model model,
                        RedirectAttributes redirectAttributes) {
        var tarefa = this.service.buscarTarefa(id);

        if (tarefa == null) {
            redirectAttributes.addFlashAttribute("erro", "Tarefa não existe no banco.");
            return "redirect:/tarefas/listar";
        }

        service.apagarTarefa(id);
        redirectAttributes.addFlashAttribute("mensagem", "Tarefa excluida com sucesso.");
        return "redirect:/tarefas/listar";
    }
    

    private void carregarTarefas(Model model) {
        var tarefas = service.buscarTarefas();
        model.addAttribute("tarefas", tarefas);
    }

}
