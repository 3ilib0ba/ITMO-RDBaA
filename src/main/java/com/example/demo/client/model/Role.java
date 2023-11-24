package com.example.demo.client.model;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "role", nullable = false)
    @JsonView
    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id + ", " +
                "role=" + role +
                "}";
    }
}
