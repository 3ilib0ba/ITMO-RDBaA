package com.example.demo.general.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ping")
public class Ping {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "time_of_ping")
    @JsonView
    private Time timeOfPing;

    @Override
    public String toString() {
        return "Ping{" +
                "id=" + id + ", " +
                "time-of-ping=" + timeOfPing +
                "}";
    }
}
