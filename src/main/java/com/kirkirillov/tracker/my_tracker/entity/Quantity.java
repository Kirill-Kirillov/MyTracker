package com.kirkirillov.tracker.my_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "quantity_test")
@Data
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quantity_id")
    private int id;

    @Column(name = "quantity_date")
    private LocalDate date;

    @Column(name = "quantity_quantity")
    private int quantity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "quantity_project_id")
    @JsonIgnore
    private Project project;

}
