package ru.registration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author MR.k0F31n
 */
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(name = "birthday")
    private LocalDate birthDay;
    @Column(name = "number_phone", nullable = false)
    private String numberPhone;
    private String email;
}
