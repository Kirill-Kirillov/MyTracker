package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.DurationDaoService;
import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.exceptionHandling.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DurationRestController {

    @Autowired
    private DurationDaoService durationDaoService;

    @Autowired
    private ProjectDaoService projectDaoService;

    @PostMapping("/duration-add-for-project/{id}")
    public Object addDuration(@PathVariable int id, @Valid @RequestBody Duration duration, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
        duration.setDate(LocalDate.now());
        duration.setProject(projectDaoService.getProject(id));
        durationDaoService.saveDuration(duration);
        return duration;
    }

    @GetMapping("/duration-get-by-id/{id}")
    public Duration getDuration(@PathVariable int id){
        if (durationDaoService.getDuration(id)==null) {
            throw new NoSuchEntityException("There is no duration with id "+id);
        }
        return durationDaoService.getDuration(id);
    }

    @GetMapping("/durations-by-project-id/{id}") //
    public List<Duration> findAllByProject(@PathVariable int id){
        Project project = projectDaoService.getProject(id);
        if (project==null) {
                throw new NoSuchEntityException("There is no project with id "+id);
        }
        List<Duration> durations = durationDaoService.findAllByProject(project);
        return durations;
    }

    @PutMapping("/duration-change-by-id/{id}")
    public Object changeDuration(@PathVariable int id,@Valid @RequestBody Duration duration, BindingResult bindingResult)
    {
        if (durationDaoService.getDuration(id)==null) {
            throw new NoSuchEntityException("There is no duration with id "+id);
        }
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
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
        if (durationDaoService.getDuration(id)==null) {
            throw new NoSuchEntityException("There is no duration with id "+id);
        }
        durationDaoService.deleteDuration(id);
        return "Удалление записи с id :"+id+" прошло успешно";
    }
}
