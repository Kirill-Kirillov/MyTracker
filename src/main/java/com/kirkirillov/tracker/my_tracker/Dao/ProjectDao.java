package com.kirkirillov.tracker.my_tracker.Dao;

import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, Integer> {
}
