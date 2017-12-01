package searchEngine.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import searchEngine.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task findById(String id);
}
