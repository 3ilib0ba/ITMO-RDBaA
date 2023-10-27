package com.example.demo.client.model;


import com.example.demo.balance.model.Balance;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonView
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonView
    private Balance balance;

    @Override
    public String toString() {
        return  "Client{" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "mail=" + mail + ", " +
                "phone=" + phone + ", " +
                "gender=" + gender + ", " +
                "role=" + role.toString() + ", " +
                "balance=" + balance.getValue().toString() +
                "}";
    }
}
