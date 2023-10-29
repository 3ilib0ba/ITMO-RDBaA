package com.example.demo.booking.model;

import com.example.demo.classes.model.Classes;
import com.example.demo.client.model.Client;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonView
    private Client client;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    @JsonView
    private Classes bookingClass;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id + ", " +
                "client=" + client + ", " +
                "booking-class=" + bookingClass +
                "}";
    }
}
