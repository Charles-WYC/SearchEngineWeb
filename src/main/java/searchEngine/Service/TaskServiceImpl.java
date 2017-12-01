package searchEngine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchEngine.model.Task;
import searchEngine.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    public TaskRepository taskRepository;

    public void save(Task task){
        taskRepository.save(task);
    }

    public Task findById(String id){
        return this.taskRepository.findById(id);
    }
}
