package br.dev.hygino.repoitory;

import br.dev.hygino.model.TodoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoDto, String> {
}
