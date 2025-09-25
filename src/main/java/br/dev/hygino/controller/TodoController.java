package br.dev.hygino.controller;

import br.dev.hygino.model.TodoDto;
import br.dev.hygino.repoitory.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        final List<TodoDto> result = todoRepository.findAll();
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
