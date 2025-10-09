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
    private String currentType; // track whether focus or break is running

    public TimerController(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    // ✅ Start Focus Timer
    @PostMapping("/start")
    public String startFocus() {
        startTime = LocalDateTime.now();
        currentType = "focus";
        return "Focus started at: " + startTime;
    }

    // ✅ Start Break Timer
    @PostMapping("/start-break")
    public String startBreak() {
        startTime = LocalDateTime.now();
        currentType = "break";
        return "Break started at: " + startTime;
    }

    // ✅ Stop Timer and Save Record
    @PostMapping("/stop")
    public Timer stopTimer() {
        if (startTime == null || currentType == null) {
            throw new IllegalStateException("No timer is currently running.");
        }

        LocalDateTime endTime = LocalDateTime.now();
        long duration = Duration.between(startTime, endTime).toSeconds();

        Timer timer = new Timer(startTime, endTime, duration);
        timer.setType(currentType); // store whether it was focus or break

        // reset state
        startTime = null;
        currentType = null;

        return timerRepository.save(timer);
    }

    // ✅ Get All Timers (History)
    @GetMapping("/all")
    public List<Timer> getAllTimers() {
        return timerRepository.findAll();
    }

    // ✅ Get Current Timer Status
    @GetMapping("/current")
    public String getCurrentStatus() {
        if (startTime == null || currentType == null) {
            return "No timer is running.";
        }
        return "Current " + currentType + " timer started at: " + startTime;
    }
}
