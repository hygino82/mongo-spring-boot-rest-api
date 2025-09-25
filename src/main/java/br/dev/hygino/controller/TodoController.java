package br.dev.hygino.controller;

import br.dev.hygino.dto.RequestTodoDto;
import br.dev.hygino.model.Todo;
import br.dev.hygino.repository.TodoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        final List<Todo> result = todoRepository.findAll();
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Todo> insert(@RequestBody @Valid RequestTodoDto dto) {
        try {
            Todo entity = new Todo(
                    null,
                    dto.title(),
                    dto.description(),
                    false,
                    LocalDate.now(),
                    null
            );
            entity = todoRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
