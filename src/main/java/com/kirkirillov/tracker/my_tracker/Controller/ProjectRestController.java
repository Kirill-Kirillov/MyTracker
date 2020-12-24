package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
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
public class ProjectRestController {

    @Autowired
    private ProjectDaoService projectDaoService;

    @PostMapping("/project")
    public Object addProject(@Valid @RequestBody Project project, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
        else {
            project.setDate(LocalDate.now());
            projectDaoService.saveProject(project);
            return project;
        }
    }

    @GetMapping("/project-by-id/{id}")
    public Project getProject(@PathVariable int id){
        if (projectDaoService.getProject(id)==null) {
            throw new NoSuchEntityException("There is no project with id " + id);
        }
        return projectDaoService.getProject(id);
    }

    @GetMapping("/projects")
    public List<Project> getAllProject(){
        List<Project> projects = projectDaoService.getAllProjects();
        return projects;
    }

    @PutMapping("/project-change-by-id/{id}")
    public Object changeProject(@PathVariable int id,@Valid @RequestBody Project project, BindingResult bindingResult)
    {
        if(projectDaoService.getProject(id)==null) {
            throw new NoSuchEntityException("There is no project with id " + id);
        }
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
        Project previousProject = projectDaoService.getProject(id);
        if (project.getName().isEmpty()) {
            project.setName(previousProject.getName());
        }
        if (project.getDate()==null) {
            project.setDate(previousProject.getDate());
        }
        project.setId(id);
        project.setType(previousProject.getType());
        project.setDurations(previousProject.getDurations());
        project.setQuantities(previousProject.getQuantities());
        projectDaoService.saveProject(project);
        return project;
    }

    @DeleteMapping("/project-delete-by-id/{id}")
    public String deleteProject(@PathVariable int id) {
        Project project = projectDaoService.getProject(id);
        if (project==null){
            throw new NoSuchEntityException("There id no project with id: "+id+" in database");
        }
        projectDaoService.deleteProject(id);
        return "Удалление записи с id :"+id+" прошло успешно";
    }

}
