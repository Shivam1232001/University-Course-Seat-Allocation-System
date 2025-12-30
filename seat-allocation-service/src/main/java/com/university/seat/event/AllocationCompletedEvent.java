package com.university.seat.event;

public class AllocationCompletedEvent {

    private String courseId;
    private String status;

    public AllocationCompletedEvent(String courseId, String status) {
        this.courseId = courseId;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }
}
