package study.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.demo.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    long countByUsername(String username);
    List<Task> findByUsername(String username);
    long countByUsernameAndCompleted(String username, boolean completed);
    
}
