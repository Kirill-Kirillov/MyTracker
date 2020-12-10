package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;

import java.util.List;

public interface DurationDaoService {
    public List<Duration> findAllByProject(Project project);

    public Duration getDuration(int id);

    public void saveDuration(Duration duration);

    public void deleteDuration(int id);
}
