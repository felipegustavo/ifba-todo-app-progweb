package com.example.todo_app_thymeleaf_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_app_thymeleaf_2.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

}
