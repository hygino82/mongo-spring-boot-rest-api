package br.dev.hygino.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "todos")
public class Todo {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean completed;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
