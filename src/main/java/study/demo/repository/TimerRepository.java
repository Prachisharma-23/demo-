package study.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import study.demo.model.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    List<Timer> findByUsername(String username);


}
