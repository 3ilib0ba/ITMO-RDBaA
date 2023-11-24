package com.example.demo.instructor.model;

import com.example.demo.classes.model.Classes;
import com.example.demo.classifiers.model.Classifier;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView
    private String name;

    @Column(name = "mail", nullable = false)
    @JsonView
    private String mail;

    @Column(name = "phone", nullable = false)
    @JsonView
    private String phone;

    @Column(name = "gender", nullable = false)
    @JsonView
    private String gender;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "instructor_class_relation",
            joinColumns = { @JoinColumn(name = "instructor_id") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    @JsonView
    private List<Classes> classes = List.of();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "instructor_classifier_relation",
            joinColumns = { @JoinColumn(name = "instructor_id") },
            inverseJoinColumns = { @JoinColumn(name = "classifier_id") }
    )
    @JsonView
    private List<Classifier> classifiers = List.of();

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "mail=" + mail + ", " +
                "phone=" + phone + ", " +
                "gender=" + gender + ", " +
                "classes=" + classes + ", " +
                "classifiers=" + classifiers +
                "}";
    }
}
