package com.kirkirillov.tracker.my_tracker.Dao;

import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.entity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityDAO extends JpaRepository<Quantity, Integer> {
    public List<Quantity> findAllByProject(Project project);

}
