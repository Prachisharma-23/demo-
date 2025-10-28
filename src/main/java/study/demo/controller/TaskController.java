package study.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Task;
import study.demo.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "https://study-planner-xi-lovat.vercel.app/")
public class TaskController {
    @Autowired
     private TaskRepository taskRepository;

    private final TaskRepository repo;
    public TaskController(TaskRepository repo) { this.repo = repo; }

    @GetMapping("/{username}")
    public List<Task> getTasksByUser(@PathVariable String username) {
      return taskRepository.findByUsername(username);
    }


    @PostMapping
    public Task add(@RequestBody Task task) {
        task.setCompleted(false);
        return repo.save(task);
    }

    @PutMapping("/{id}/complete")
    public Task markComplete(@PathVariable Long id) {
        Task task = repo.findById(id).orElseThrow();
        task.setCompleted(true);
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

