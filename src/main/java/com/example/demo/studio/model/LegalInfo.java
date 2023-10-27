package com.example.demo.studio.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "legal_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LegalInfo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    @Column(name = "full_description")
    @JsonView
    private String fullDescription;

    @Column(name = "contact_phone")
    @JsonView
    private String phone;

    @Column(name = "mail", nullable = false)
    @JsonView
    private String mail;

    @Column(name = "TIN", nullable = false)
    @JsonView
    private String TIN;

    @Override
    public String toString() {
        return "LegalInfo{" +
                "id=" + id + ", " +
                "full-description=" + fullDescription + ", " +
                "phone=" + phone + ", " +
                "mail=" + mail + ", " +
                "TIN=" + TIN +
                "}";
    }
}
