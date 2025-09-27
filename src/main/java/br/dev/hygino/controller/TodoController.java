package br.dev.hygino.controller;

import br.dev.hygino.dto.RequestTodoDto;
import br.dev.hygino.model.Todo;
import br.dev.hygino.repository.TodoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                    dto.completed() != null ? dto.completed() : false,
                    LocalDateTime.now(),
                    null
            );
            entity = todoRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        final Optional<Todo> result = todoRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo with id: " + id + " not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid RequestTodoDto dto) {
        final Optional<Todo> result = todoRepository.findById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo with id: " + id + " not found!");
        }

        var document = result.get();
        document.setUpdatedAt(LocalDateTime.now());
        document.setDescription(dto.description());
        document.setTitle(dto.title());
        document.setCompleted(dto.completed() != null ? dto.completed() : document.isCompleted());

        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
