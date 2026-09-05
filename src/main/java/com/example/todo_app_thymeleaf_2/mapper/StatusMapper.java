package com.example.todo_app_thymeleaf_2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.todo_app_thymeleaf_2.dto.StatusDTO;
import com.example.todo_app_thymeleaf_2.entity.StatusEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatusMapper {

    StatusEntity toEntity(StatusDTO dto);

    StatusDTO toDto(StatusEntity entity);

}
