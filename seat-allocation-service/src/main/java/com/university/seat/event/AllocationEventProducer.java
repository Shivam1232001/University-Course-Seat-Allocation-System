package com.university.seat.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AllocationEventProducer {

    private static final String TOPIC = "allocation.completed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AllocationEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAllocationCompleted(String courseId) {
        try {
            AllocationCompletedEvent event =
                    new AllocationCompletedEvent(courseId, "COMPLETED");
            kafkaTemplate.send(TOPIC, event);
        } catch (Exception e) {
            // Ignore if Kafka is not available (for local/dev)
            System.out.println("Kafka not available, skipping event publish");
        }
    }
}
