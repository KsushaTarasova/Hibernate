package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Integer age;
}
