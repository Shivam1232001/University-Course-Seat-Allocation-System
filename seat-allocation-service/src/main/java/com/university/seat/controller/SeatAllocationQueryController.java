package com.university.seat.controller;

import com.university.seat.entity.Allocation;
import com.university.seat.repository.AllocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
public class SeatAllocationQueryController {

    private final AllocationRepository repository;

    public SeatAllocationQueryController(AllocationRepository repository) {
        this.repository = repository;
    }

    // QUERY → read-only
    @GetMapping("/{courseId}")
    public List<Allocation> getResult(@PathVariable String courseId) {
        return repository.findByCourseId(courseId);
    }
}
