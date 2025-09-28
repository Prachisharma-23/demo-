package study.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import study.demo.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    long countByCompleted(boolean b);
    
}
