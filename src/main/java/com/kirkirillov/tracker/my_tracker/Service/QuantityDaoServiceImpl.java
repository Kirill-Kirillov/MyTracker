package com.kirkirillov.tracker.my_tracker.Service;

import com.kirkirillov.tracker.my_tracker.Dao.QuantityDAO;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.entity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuantityDaoServiceImpl implements QuantityDaoService{

    @Autowired
    private QuantityDAO quantityDAO;

    @Override
    public List<Quantity> findAllByProject(Project project) {
        List<Quantity> quantities = quantityDAO.findAllByProject(project);
        return quantities;
    }

    public Quantity getQuantity(int id) {
        Quantity quantity = null;
        Optional<Quantity> optionalDuration = quantityDAO.findById(id);
        if(optionalDuration.isPresent()) {
            quantity = optionalDuration.get();
        }
        return quantity;
    }

    @Override
    public void saveQuantity(Quantity quantity) {
        quantityDAO.save(quantity);
    }

    @Override
    public void deleteQuantity(int id) {
        quantityDAO.deleteById(id);
    }
}
