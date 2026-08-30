package com.example.todo_app_thymeleaf_2;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_thymeleaf_2.constants.MensagemErro;
import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;
import com.example.todo_app_thymeleaf_2.exception.AppTarefaException;
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
        if (!repository.existsById(dto.getId())) {
            throw lancarErroTarefaNaoEncontrada(dto.getId());
        }
        repository.save(mapper.toEntity(dto));
    }

    public void apagarTarefa(Long id) {
        if (!repository.existsById(id)) {
            throw lancarErroTarefaNaoEncontrada(id);
        }
        repository.deleteById(id);
    }

    public TarefaDTO buscarTarefa(Long id) {
        return repository
                    .findById(id)
                    .map(t -> mapper.toDto(t))
                    .orElseThrow(
                        () -> lancarErroTarefaNaoEncontrada(id));
    }

    public List<TarefaDTO> buscarTarefas() {
        return repository
            .findAll()
            .stream()
            .map(t -> mapper.toDto(t))
            .toList();
    }

    private AppTarefaException lancarErroTarefaNaoEncontrada(Long id) {
        return new AppTarefaException(MensagemErro.ERRO_TAREFA_NAO_ENCONTRADA.formatted(id), 404);
    }

}
