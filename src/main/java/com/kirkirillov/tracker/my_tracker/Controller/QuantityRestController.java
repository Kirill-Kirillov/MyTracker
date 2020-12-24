package com.kirkirillov.tracker.my_tracker.Controller;

import com.kirkirillov.tracker.my_tracker.Service.ProjectDaoService;
import com.kirkirillov.tracker.my_tracker.Service.QuantityDaoService;
import com.kirkirillov.tracker.my_tracker.entity.Duration;
import com.kirkirillov.tracker.my_tracker.entity.Project;
import com.kirkirillov.tracker.my_tracker.entity.Quantity;
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
public class QuantityRestController {
    @Autowired
    private QuantityDaoService quantityDaoService;
    @Autowired
    private ProjectDaoService projectDaoService; // для получения записей по проекту

    @PostMapping("/quantity-add-for-project/{id}")
    public Object addQuantity(@PathVariable int id, @Valid @RequestBody Quantity quantity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
        quantity.setDate(LocalDate.now());
        quantity.setProject(projectDaoService.getProject(id));
        quantityDaoService.saveQuantity(quantity);
        return quantity;
    }

    @GetMapping("/quantity-by-id/{id}")
    public Quantity getQuantity(@PathVariable int id){
        if (quantityDaoService.getQuantity(id)==null) {
            throw new NoSuchEntityException("There is no quantity with id "+id);
        }
        return quantityDaoService.getQuantity(id);
    }

    @GetMapping("/quantities-by-project/{id}") //
    public List<Quantity> findAllByProject(@PathVariable int id){
        if (projectDaoService.getProject(id)==null) {
            throw new NoSuchEntityException("There is no project with id "+id);
        }
        Project project = projectDaoService.getProject(id);
        List<Quantity> quantities = quantityDaoService.findAllByProject(project);
        return quantities;
    }

    @PutMapping("/quantity-change-by-id/{id}")
    public Object changeProject(@PathVariable int id, @Valid @RequestBody Quantity quantity, BindingResult bindingResult){
        if (quantityDaoService.getQuantity(id)==null) {
            throw new NoSuchEntityException("There is no quantity with id "+id);
        }
        if(bindingResult.hasErrors()) {
            Map<String, String> validatorMessageErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validatorMessageErrors.put(error.getField(), error.getDefaultMessage());
            }
            return validatorMessageErrors;
        }
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
        if (quantityDaoService.getQuantity(id)==null) {
            throw new NoSuchEntityException("There is no quantity with id "+id);
        }
        quantityDaoService.deleteQuantity(id);
        return "Удаление записи с id :"+id+" прошло успешно";
    }
}
