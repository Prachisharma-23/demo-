package study.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Growth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int completedTasks;
    private int studyHours;
    private int growthPercentage;

    public Growth() {}

    public Growth(int completedTasks, int studyHours, int growthPercentage) {
        this.completedTasks = completedTasks;
        this.studyHours = studyHours;
        this.growthPercentage = growthPercentage;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(int studyHours) {
        this.studyHours = studyHours;
    }

    public int getGrowthPercentage() {
        return growthPercentage;
    }

    public void setGrowthPercentage(int growthPercentage) {
        this.growthPercentage = growthPercentage;
    }
}
