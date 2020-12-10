package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
import com.kirkirillov.tracker.my_tracker.Service.QuantityDaoService;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.entity.Quantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class QuantityRestController {
    @Autowired
    private QuantityDaoService quantityDaoService;
    @Autowired
    private ProjectDaoService projectDaoService; // для получения записей по проекту

    @PostMapping("/quantity-add-for-project/{id}")
    public Quantity addQuantity(@PathVariable int id, @RequestBody Quantity quantity) {
        quantity.setDate(LocalDate.now());
        quantity.setProject(projectDaoService.getProject(id));
        quantityDaoService.saveQuantity(quantity);
        return quantity;
    }

    @GetMapping("/quantity-by-id/{id}")
    public Quantity getQuantity(@PathVariable int id){
        return quantityDaoService.getQuantity(id);
    }

    @GetMapping("/quantities-by-project/{id}") //
    public List<Quantity> findAllByProject(@PathVariable int id){
        Project project = projectDaoService.getProject(id);
        List<Quantity> quantities = quantityDaoService.findAllByProject(project);
        return quantities;
    }

    @PutMapping("/quantity-change-by-id/{id}")
    public Quantity changeProject(@PathVariable int id, @RequestBody Quantity quantity){
        Quantity previosQuantity = quantityDaoService.getQuantity(id);
        quantity.setProject(previosQuantity.getProject());
        if (quantity.getDate()==null){
            quantity.setDate(previosQuantity.getDate());
        }
        if (quantity.getQuantity()==0){
            quantity.setQuantity(previosQuantity.getQuantity());
        }
        quantity.setId(id);
        quantityDaoService.saveQuantity(quantity);
        return quantity;
    }

    @DeleteMapping("/quantity-delete-by-id/{id}")
    public String deleteProject(@PathVariable int id) {
        quantityDaoService.deleteQuantity(id);
        return "Удаление записи с id :"+id+" прошло успешно";
    }
}
