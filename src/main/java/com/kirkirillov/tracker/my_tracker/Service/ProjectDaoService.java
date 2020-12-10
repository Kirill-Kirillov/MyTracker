package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.entity.Project;

import java.util.List;

public interface ProjectDaoService {
    public List<Project> getAllProjects();

    public Project getProject(int id);

    public void saveProject(Project project);

    public void deleteProject(int id);
}
