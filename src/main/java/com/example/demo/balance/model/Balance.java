package com.example.demo.balance.model;


import com.example.demo.client.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Balance {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "value")
    @JsonView
    private Float value;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonView
    private Role role;

    @Override
    public String toString() {
        return "Balance{" +
                "value=" + value + ", " +
                "role=" + role +
                "}";
    }
}
