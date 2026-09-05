package com.example.todo_app_thymeleaf_2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;
import com.example.todo_app_thymeleaf_2.entity.StatusEntity;
import com.example.todo_app_thymeleaf_2.entity.TarefaEntity;
import com.example.todo_app_thymeleaf_2.mapper.TarefaMapper;
import com.example.todo_app_thymeleaf_2.repository.StatusRepository;
import com.example.todo_app_thymeleaf_2.repository.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final StatusRepository statusRepository;
    private final TarefaMapper mapper;

    public void criarTarefa(TarefaDTO dto) {
        tarefaRepository.save(carregarStatus(dto));
    }

    private TarefaEntity carregarStatus(TarefaDTO dto) {
        StatusEntity status = statusRepository.findById(dto.getStatusId()).orElseThrow();
        TarefaEntity tarefa = mapper.toEntity(dto);
        tarefa.setStatus(status);
        return tarefa;
    }

    public void atualizarTarefa(TarefaDTO dto) {
        if (tarefaRepository.existsById(dto.getId())) {
            tarefaRepository.save(carregarStatus(dto));
        }
    }

    public void apagarTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
        }
    }

    public TarefaDTO buscarTarefa(Long id) {
        return tarefaRepository
                    .findById(id)
                    .map(t -> mapper.toDto(t))
                    .orElse(null);
    }

    public List<TarefaDTO> buscarTarefas() {
        return tarefaRepository
            .findAll()
            .stream()
            .map(t -> mapper.toDto(t))
            .toList();
    }

}
