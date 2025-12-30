package com.university.seat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    private Long studentId;

    private Double cgpa;

    private String category;

    // getters & setters
}
