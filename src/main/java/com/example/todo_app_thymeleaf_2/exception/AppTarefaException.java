package com.example.todo_app_thymeleaf_2.exception;

import lombok.Getter;

@Getter
public class AppTarefaException extends RuntimeException {

    private int status;

    public AppTarefaException(String msg, int status) {
        super(msg);
        this.status = status;
    }

}
