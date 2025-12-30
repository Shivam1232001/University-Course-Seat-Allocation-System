package com.university.seat.controller;

import com.university.seat.service.SeatAllocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocation")
public class SeatAllocationCommandController {

    private final SeatAllocationService service;

    public SeatAllocationCommandController(SeatAllocationService service) {
        this.service = service;
    }

    // COMMAND → modifies database
    @PostMapping("/{courseId}")
    public String allocate(@PathVariable String courseId) {
        service.allocateSeats(courseId);
        return "Seat allocation completed for course " + courseId;
    }
}

