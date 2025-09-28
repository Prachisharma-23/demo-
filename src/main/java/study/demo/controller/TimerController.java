package study.demo.controller;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Timer;
import study.demo.repository.TimerRepository;

@RestController
@RequestMapping("/api/timer")
@CrossOrigin(origins = "http://localhost:3000") // React port
public class TimerController {

    private final TimerRepository timerRepository;
    private LocalDateTime startTime;

    public TimerController(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    @PostMapping("/start")
    public String startTimer() {
        startTime = LocalDateTime.now();
        return "Timer started at: " + startTime;
    }

    @PostMapping("/stop")
    public Timer stopTimer() {
        LocalDateTime endTime = LocalDateTime.now();
        long duration = Duration.between(startTime, endTime).toSeconds();

        Timer timer = new Timer(startTime, endTime, duration);
        return timerRepository.save(timer);
    }

    @GetMapping("/all")
    public List<Timer> getAllTimers() {
        return timerRepository.findAll();
    }
}
