package study.demo.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Timer;
import study.demo.repository.TimerRepository;
import study.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/timer")
@CrossOrigin(origins = "http://localhost:3000") // React app
public class TimerController {

    private final TimerRepository timerRepository;
    private final UserRepository userRepository;

    private LocalDateTime startTime;
    private String currentType;
    private String currentUsername; // currently active user

    public TimerController(TimerRepository timerRepository, UserRepository userRepository) {
        this.timerRepository = timerRepository;
        this.userRepository = userRepository;
    }

    // ✅ Start Focus Timer
    @PostMapping("/start/{username}")
    public String startFocus(@PathVariable String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        startTime = LocalDateTime.now();
        currentType = "focus";
        currentUsername = username;

        return username + " started focus at: " + startTime;
    }

    // ✅ Start Break Timer
    @PostMapping("/start-break/{username}")
    public String startBreak(@PathVariable String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        startTime = LocalDateTime.now();
        currentType = "break";
        currentUsername = username;

        return username + " started break at: " + startTime;
    }

    // ✅ Stop Timer and Save Record
    @PostMapping("/stop/{username}")
    public Timer stopTimer(@PathVariable String username) {
        if (startTime == null || currentType == null || currentUsername == null) {
            throw new IllegalStateException("No timer is currently running.");
        }

        if (!currentUsername.equals(username)) {
            throw new IllegalStateException("This user has no active timer.");
        }

        LocalDateTime endTime = LocalDateTime.now();
        long duration = Duration.between(startTime, endTime).toSeconds();

        Timer timer = new Timer(startTime, endTime, duration, username, username);
        timer.setType(currentType);
        timer.setUsername(currentUsername); // ✅ set username directly

        // reset
        startTime = null;
        currentType = null;
        currentUsername = null;

        return timerRepository.save(timer);
    }

    // ✅ Get All Timers for Specific User
    @GetMapping("/all/{username}")
    public List<Timer> getAllTimersForUser(@PathVariable String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return timerRepository.findByUsername(username); // ✅ use username
    }

    // ✅ Get Current Timer Status
    @GetMapping("/current")
    public String getCurrentStatus() {
        if (startTime == null || currentType == null || currentUsername == null) {
            return "No timer is running.";
        }
        return "Current " + currentType + " timer for " + currentUsername + " started at: " + startTime;
    }
}

