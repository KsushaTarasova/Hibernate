package org.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record Birthday(LocalDate birthDate) {
    public long getAge(){
        return ChronoUnit.YEARS.between(birthDate, LocalDateTime.now());
    }
}
