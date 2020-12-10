package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.Dao.ProjectDao;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectDaoServiceImpl implements ProjectDaoService{

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = projectDao.findAll();
        return projects;
    }

    @Override
    public Project getProject(int id) {
        Project project = null;
        Optional<Project> optional = projectDao.findById(id);
        if (optional.isPresent()) {
            project = optional.get();
        }
        return project;
    }

    @Override
    public void saveProject(Project project) {
        if(project.getDate()==null){
            project.setDate(LocalDate.now());
        }
        projectDao.save(project);
    }

    @Override
    public void deleteProject(int id) {
        projectDao.delete(getProject(id));
    }
}
