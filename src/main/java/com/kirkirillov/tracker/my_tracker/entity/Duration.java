package com.kirkirillov.tracker.my_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "duration_test")
@Data
public class Duration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "duration_id")
    private int id;

    @Column(name = "duration_date")
    private LocalDate date;

    @Column(name = "duration_duration")
    private Double duration;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "duration_project_id")
    @JsonIgnore
    private Project project;
}
