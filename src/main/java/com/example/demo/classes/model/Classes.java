package com.example.demo.classes.model;

import com.example.demo.classifiers.model.Classifier;
import com.example.demo.studio.model.Position;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class Classes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView
    private String name;

    @Column(name = "date_of", nullable = false)
    @JsonView
    private Date date;

    @Column(name = "start_time", nullable = false)
    @JsonView
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    @JsonView
    private Time endTime;

    @Column(name = "amount")
    @JsonView
    private float amount;

    @ManyToOne
    @JoinColumn(name = "pos_id", nullable = false)
    @JsonView
    private Position position;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "class_classifier_relation",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "classifier_id") }
    )
    @JsonView
    private List<Classifier> classifiers = List.of();

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "date=" + date + ", " +
                "start-time=" + startTime + ", " +
                "end-time=" + endTime + ", " +
                "amount=" + amount + ", " +
                "position=" + position + ", " +
                "classifiers=" + classifiers +
                "}";
    }
}
