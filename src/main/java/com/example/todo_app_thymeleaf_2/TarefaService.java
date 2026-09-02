package com.example.todo_app_thymeleaf_2;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;
import com.example.todo_app_thymeleaf_2.mapper.TarefaMapper;
import com.example.todo_app_thymeleaf_2.repository.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    public void criarTarefa(TarefaDTO dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void atualizarTarefa(TarefaDTO dto) {
        if (repository.existsById(dto.getId())) {
            repository.save(mapper.toEntity(dto));
        }
    }

    public void apagarTarefa(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public TarefaDTO buscarTarefa(Long id) {
        return repository
                    .findById(id)
                    .map(t -> mapper.toDto(t))
                    .orElse(null);
    }

    public List<TarefaDTO> buscarTarefas() {
        return repository
            .findAll()
            .stream()
            .map(t -> mapper.toDto(t))
            .toList();
    }

}
