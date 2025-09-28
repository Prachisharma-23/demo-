package study.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.demo.model.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> {


}
