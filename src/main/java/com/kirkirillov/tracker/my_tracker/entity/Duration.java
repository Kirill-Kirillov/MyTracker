package com.kirkirillov.tracker.my_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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
    @Min(value = 1, message = "Продолжительность должна быть больше 0")
    private Double duration;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "duration_project_id")
    @JsonIgnore
    private Project project;
}
