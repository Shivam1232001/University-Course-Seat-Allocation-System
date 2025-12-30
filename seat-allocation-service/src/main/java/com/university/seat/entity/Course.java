package com.university.seat.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String courseId;

    private Integer seatLimit;

    // getters & setters
}
