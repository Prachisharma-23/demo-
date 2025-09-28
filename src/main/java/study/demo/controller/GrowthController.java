package study.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Timer;
import study.demo.repository.TaskRepository;
import study.demo.repository.TimerRepository;

@RestController
@RequestMapping("/api/growth")
@CrossOrigin(origins = "http://localhost:3000") 
public class GrowthController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TimerRepository timerRepository;

    @GetMapping
    public Map<String, Object> getGrowth() {
        // total tasks
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByCompleted(true);

        // growth percentage
        double growthPercent = totalTasks > 0 ? (completedTasks * 100.0 / totalTasks) : 0.0;

        // study hours from timers
        List<Timer> sessions = timerRepository.findAll();
        long totalSeconds = sessions.stream()
                                    .mapToLong(Timer::getDuration)
                                    .sum();
        double studyHours = totalSeconds / 3600.0;

        // response map
        Map<String, Object> growth = new HashMap<>();
        growth.put("totalTasks", totalTasks);
        growth.put("completedTasks", completedTasks);
        growth.put("growthPercent", growthPercent);
        growth.put("studyHours", studyHours);

        return growth;
    }
}
