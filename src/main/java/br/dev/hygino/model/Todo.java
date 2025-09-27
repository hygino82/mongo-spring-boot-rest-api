package br.dev.hygino.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    @NotBlank(message = "O título não pode estar em branco")
    @Size(min = 3, max = 40, message = "O título deve ter entre 3 e 40 caracteres")
    private String title;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(min = 3, max = 40, message = "a descrição deve ter entre 3 e 100 caracteres")
    private String description;

    @NotNull
    private Boolean completed;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
