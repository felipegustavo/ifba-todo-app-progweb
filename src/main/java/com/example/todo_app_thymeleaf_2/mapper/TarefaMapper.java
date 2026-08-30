package com.example.todo_app_thymeleaf_2.mapper;

import org.mapstruct.Mapper;

import com.example.todo_app_thymeleaf_2.dto.TarefaDTO;
import com.example.todo_app_thymeleaf_2.entity.TarefaEntity;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaEntity toEntity(TarefaDTO dto);

    TarefaDTO toDto(TarefaEntity entity);

}
