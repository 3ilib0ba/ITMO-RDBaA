package com.example.demo.studio.model;

import com.example.demo.classifiers.model.Classifier;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pos")
public class Position {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "address", nullable = false)
    @JsonView
    private String address;

    @Column(name = "working_hours", nullable = false)
    @JsonView
    private String hours;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    @JsonView
    private Studio studio;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "pos_classifier_relation",
            joinColumns = { @JoinColumn(name = "pos_id") },
            inverseJoinColumns = { @JoinColumn(name = "classifier_id") }
    )
    @JsonView
    private List<Classifier> classifiers = List.of();

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id + ", " +
                "address=" + address + ", " +
                "hours=" + hours + ", " +
                "studio-id=" + studio.getId() + ", " +
                "classifiers=" + classifiers +
                "}";
    }
}
