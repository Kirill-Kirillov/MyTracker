package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.DurationDaoService;
import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DurationRestController {

    @Autowired
    private DurationDaoService durationDaoService;

    @Autowired
    private ProjectDaoService projectDaoService;

    @PostMapping("/duration-add-for-project/{id}")
    public Duration addDuration(@PathVariable int id, @RequestBody Duration duration) {
        duration.setDate(LocalDate.now());
        duration.setProject(projectDaoService.getProject(id));
        durationDaoService.saveDuration(duration);
        return duration;
    }

    @GetMapping("/duration-get-by-id/{id}")
    public Duration getDuration(@PathVariable int id){
        return durationDaoService.getDuration(id);
    }

    @GetMapping("/durations-by-project-id/{id}") //
    public List<Duration> findAllByProject(@PathVariable int id){
        Project project = projectDaoService.getProject(id);
        List<Duration> durations = durationDaoService.findAllByProject(project);
        return durations;
    }

    @PutMapping("/duration-change-by-id/{id}")
    public Duration changeDuration(@PathVariable int id, @RequestBody Duration duration)
    {
        Duration previosDuration = durationDaoService.getDuration(id);
        duration.setProject(previosDuration.getProject());
        if (duration.getDate()==null){
            duration.setDate(previosDuration.getDate());
        }
        if (duration.getDuration()==null||duration.getDuration()==0){
            duration.setDuration(previosDuration.getDuration());
        }
        duration.setId(id);
        durationDaoService.saveDuration(duration);
        return duration;
    }

    @DeleteMapping("/duration-delete-by-id/{id}")
    public String deleteProject(@PathVariable int id) {
        durationDaoService.deleteDuration(id);
        return "Удалление записи с id :"+id+" прошло успешно";
    }
}
