package com.university.seat.service;


import com.university.seat.entity.Allocation;
import com.university.seat.entity.Course;
import com.university.seat.entity.Student;
import com.university.seat.event.AllocationEventProducer;
import com.university.seat.repository.AllocationRepository;
import com.university.seat.repository.CourseRepository;
import com.university.seat.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SeatAllocationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AllocationRepository allocationRepository;

    private final AllocationEventProducer eventProducer;

    public SeatAllocationService(StudentRepository studentRepository, CourseRepository courseRepository, AllocationRepository allocationRepository, AllocationEventProducer eventProducer) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.allocationRepository = allocationRepository;
        this.eventProducer = eventProducer;
    }


    /**
     * Core seat allocation logic
     */
    public void allocateSeats(String courseId) {

        // 1. Fetch course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        int seatLimit = course.getSeatLimit();

        // 2. Fetch all students
        List<Student> students = studentRepository.findAll();

        // 3. Sort students by CGPA (DESC)
        students.sort(
                Comparator.comparing(Student::getCgpa).reversed()
        );

        // 4. Allocate seats
        int allocatedCount = 0;

        for (Student student : students) {

            Allocation allocation = new Allocation();
            allocation.setStudentId(student.getStudentId());
            allocation.setCourseId(courseId);

            if (allocatedCount < seatLimit) {
                allocation.setStatus("ALLOCATED");
                allocatedCount++;
            } else {
                allocation.setStatus("WAITLISTED");
            }

            allocationRepository.save(allocation);
        }

//        eventProducer.publishAllocationCompleted((courseId));
    }
}

