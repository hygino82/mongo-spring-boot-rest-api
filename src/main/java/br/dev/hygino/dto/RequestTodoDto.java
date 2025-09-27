package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestTodoDto(
        @NotBlank(message = "O título não pode estar em branco")
        @Size(
                max = 40,
                min = 3,
                message = "O título deve ter entre 3 e 40 caracteres"
        ) String title,

        @NotBlank(message = "A descrição não pode estar em branco")
        @Size(
                max = 100,
                min = 3,
                message = "A descrição deve ter entre 3 e 100 caracteres"
        ) String description,
        Boolean completed
) {
}
