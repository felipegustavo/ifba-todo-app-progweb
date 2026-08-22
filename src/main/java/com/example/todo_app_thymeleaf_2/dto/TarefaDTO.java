package com.example.todo_app_thymeleaf_2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TarefaDTO {

    private Long id;

    @NotBlank
    @Size(min = 10, max = 50)
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private String status;

    private String textoStatus;


    public String getTextoStatus() {
        switch (status) {
            case "TODO":
                return "Para Fazer";
            case "DOING":
                return "Fazendo";
            case "DONE":
                return "Feito";
            default:
                return "";
        }
    }

}
