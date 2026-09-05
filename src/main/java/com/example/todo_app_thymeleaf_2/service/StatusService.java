package com.example.todo_app_thymeleaf_2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_thymeleaf_2.dto.StatusDTO;
import com.example.todo_app_thymeleaf_2.entity.StatusEntity;
import com.example.todo_app_thymeleaf_2.mapper.StatusMapper;
import com.example.todo_app_thymeleaf_2.repository.StatusRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final StatusRepository repository;
    private final StatusMapper mapper;

    public List<StatusDTO> listar() {
        List<StatusDTO> result = new ArrayList<>();
        for (StatusEntity e : repository.findAll()) {
            result.add(mapper.toDto(e));
        }
        return result;
    }

}
