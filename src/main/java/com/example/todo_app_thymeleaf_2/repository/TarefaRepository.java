package com.example.todo_app_thymeleaf_2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_app_thymeleaf_2.entity.TarefaEntity;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {

    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    //List<TarefaEntity> findByTituloLike(String titulo);

    // https://www.baeldung.com/spring-data-jpa-query
    //@Query(value = "select * from TB_TAREFA where titulo = :titulo", nativeQuery = true)
    //List<TarefaEntity> findBy2(@Param("titulo") String titulo);


}
