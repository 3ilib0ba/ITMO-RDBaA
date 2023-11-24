package com.example.demo.studio.model;

import com.example.demo.balance.model.Balance;
import com.example.demo.client.model.Role;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studio")
public class Studio {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonView
    private String name;

    @Column(name = "description")
    @JsonView
    private String description;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonView
    private Role role;

    @OneToOne
    @JsonView
    private LegalInfo legalInfo;

    @OneToOne
    @JsonView
    private Balance balance;

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "role=" + role + ", " +
                "legal-info=" + legalInfo + ", " +
                "balance=" + balance +
                "}";
    }
}
