package searchEngine.Service;

import org.springframework.stereotype.Repository;
import searchEngine.model.Task;

@Repository
public interface TaskService {
    void save(Task task);
    Task findById(String id);
}
