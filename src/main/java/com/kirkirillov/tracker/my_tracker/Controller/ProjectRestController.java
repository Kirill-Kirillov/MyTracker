package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectRestController {

    @Autowired
    private ProjectDaoService projectDaoService;

    @PostMapping("/project")
    public Project addProject(@RequestBody Project project)
    {
        project.setDate(LocalDate.now());
        projectDaoService.saveProject(project);
        return project;
    }

    @GetMapping("/project-by-id/{id}")
    public Project getProject(@PathVariable int id){
        return projectDaoService.getProject(id);
    }

    @GetMapping("/projects")
    public List<Project> getAllProject(){
        List<Project> projects = projectDaoService.getAllProjects();
        return projects;
    }

    @PutMapping("/project-change-by-id/{id}")
    public Project changeProject(@PathVariable int id, @RequestBody Project project)
    {
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
        projectDaoService.deleteProject(id);
        return "Удалление записи с id :"+id+" прошло успешно";
    }

}
