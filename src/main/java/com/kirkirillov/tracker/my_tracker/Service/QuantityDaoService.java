package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.entity.Quantity;

import java.util.List;

public interface QuantityDaoService {
    public List<Quantity> findAllByProject(Project project);

    public Quantity getQuantity(int id);

    public void saveQuantity(Quantity quantity);

    public void deleteQuantity(int id);
}
