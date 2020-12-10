package com.kirkirillov.tracker.my_tracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name ="project_test")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int id;

    @Column(name = "project_date")
    private LocalDate date;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_type")
    private String type;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "project")
    private List<Duration> durations;

    public void addDuration(Duration duration){
        if(durations==null) {
            durations=new ArrayList<>();
        }
        durations.add(duration);
        duration.setProject(this);
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "project")
    private List<Quantity> quantities;

    public void addQuantity(Quantity quantity){
        if(quantities==null) {
            quantities=new ArrayList<>();
        }
        quantities.add(quantity);
        quantity.setProject(this);
    }
}
