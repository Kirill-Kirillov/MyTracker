package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.Dao.DurationDAO;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DurationDaoServiceImpl implements DurationDaoService{

    @Autowired
    private DurationDAO durationDAO;

    @Override
    public List<Duration> findAllByProject(Project project) {
        List<Duration> durations = durationDAO.findAllByProject(project);
        return durations;
    }

    @Override
    public Duration getDuration(int id) {
        Duration duration = null;
        Optional<Duration> optionalDuration = durationDAO.findById(id);
        if(optionalDuration.isPresent()) {
            duration = optionalDuration.get();
        }
        return duration;
    }

    @Override
    public void saveDuration(Duration duration) {
        durationDAO.save(duration);
    }

    @Override
    public void deleteDuration(int id) {
        durationDAO.deleteById(id);
    }

}
