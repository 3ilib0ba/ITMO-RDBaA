package com.example.demo.classifiers.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classifier")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classifier {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "classifier_value", nullable = false)
    @JsonView
    private String value;

    @Column(name = "classifier_name", nullable = false)
    @JsonView
    private String name;

    @Override
    public String toString() {
        return "Classifier{" +
                "value=" + value + ", " +
                "name=" + name +
                "}";
    }
}
