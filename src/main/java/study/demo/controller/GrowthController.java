package study.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.demo.model.Timer;
import study.demo.repository.TaskRepository;
import study.demo.repository.TimerRepository;

@RestController
@RequestMapping("/api/growth")
@CrossOrigin(origins ="https://study-planner-xi-lovat.vercel.app/") 

public class GrowthController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TimerRepository timerRepository;

   
    // ✅ Fetch growth for a specific user
    @GetMapping("/{username}")
    public Map<String, Object> getGrowthByUser(@PathVariable String username) {

        long totalTasks = taskRepository.countByUsername(username);
        long completedTasks = taskRepository.countByUsernameAndCompleted(username, true);

        double growthPercent = totalTasks > 0 ? (completedTasks * 100.0 / totalTasks) : 0.0;

        List<Timer> sessions = timerRepository.findByUsername(username);
        long totalSeconds = sessions.stream()
                                    .mapToLong(Timer::getDuration)
                                    .sum();
        double studyHours = totalSeconds / 3600.0;

        Map<String, Object> growth = new HashMap<>();
        growth.put("totalTasks", totalTasks);
        growth.put("completedTasks", completedTasks);
        growth.put("growthPercent", growthPercent);
        growth.put("studyHours", studyHours);
        growth.put("username", username);

        return growth;
    }
}
