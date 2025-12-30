package com.university.seat.repository;

import com.university.seat.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByCourseId(String courseId);

    List<Allocation> findByStudentId(Long studentId);
}
