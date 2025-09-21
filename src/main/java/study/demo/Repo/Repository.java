package study.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import study.demo.model.Task;

public interface Repository extends JpaRepository<Task, Long> {
    
}
