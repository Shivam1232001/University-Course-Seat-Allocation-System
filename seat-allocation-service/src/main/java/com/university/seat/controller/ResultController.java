package com.university.seat.controller;

import com.university.seat.entity.Allocation;
import com.university.seat.repository.AllocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final AllocationRepository allocationRepository;

    public ResultController(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @GetMapping("/student/{studentId}")
    public List<Allocation> getStudentResult(@PathVariable Long studentId) {
        return allocationRepository.findByStudentId(studentId);
    }
}
