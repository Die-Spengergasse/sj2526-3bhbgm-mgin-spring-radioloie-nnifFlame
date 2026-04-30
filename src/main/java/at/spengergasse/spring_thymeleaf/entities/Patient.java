package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @NotBlank(message = "Sozialversicherungsnummer darf nicht leer sein")
    @Pattern(regexp = "^[0-9]{4}\\s?[0-9]{6}$", message = "Ungültige Sozialversicherungsnummer (Format: 1234 010170)")
    private String socialSecurityNumber;

    @NotBlank(message = "Vorname darf nicht leer sein")
    private String firstName;

    @NotBlank(message = "Nachname darf nicht leer sein")
    private String lastName;

    private String gender;

    @PastOrPresent(message = "Geburtsdatum darf nicht in der Zukunft liegen")
    private LocalDate birthDate;

    public Patient() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
