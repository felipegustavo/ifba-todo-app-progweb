package com.example.todo_app_thymeleaf_2.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;


@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping({"/", "/listar"})
    public String listar(Model model) {
        List<TarefaDTO> tarefas = new ArrayList<>();
        tarefas.add(new TarefaDTO(1L, "Teste", "Teste", "TODO", null));
        tarefas.add(new TarefaDTO(2L, "Teste", "Teste", "DOING", null));
        tarefas.add(new TarefaDTO(3L, "Teste", "Teste", "DONE", null));

        model.addAttribute("tarefa", new TarefaDTO());
        model.addAttribute("tarefas", tarefas);

        return "tarefas";
    }
    

}
