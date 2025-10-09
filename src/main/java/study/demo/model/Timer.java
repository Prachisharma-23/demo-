package study.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration; // in seconds
    private String type; // "work" or "break"

    public Timer() {}

    public Timer(LocalDateTime startTime, LocalDateTime endTime, Long duration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Long getDuration() { return duration; }
    public void setDuration(Long duration) { this.duration = duration; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}


