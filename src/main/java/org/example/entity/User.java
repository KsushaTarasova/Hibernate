package org.example.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data //геттеры, сеттеры
@NoArgsConstructor
@AllArgsConstructor
@Builder //паттерн строитель
@Entity
public class User {
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Integer age;
}
