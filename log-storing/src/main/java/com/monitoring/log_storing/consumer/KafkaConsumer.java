package com.monitoring.log_storing.consumer;

import com.monitoring.log_storing.repository.LogStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.monitoring.log_storing.entity.SystemMetrics;

import java.util.UUID;

@Service
public class KafkaConsumer {

    private final LogStorageRepository logStorageRepository;
    @Autowired
    public KafkaConsumer(LogStorageRepository logStorageRepository) {
        this.logStorageRepository = logStorageRepository;
    }

    @KafkaListener(topics = "system-metrics", groupId = "system-monitor-group")
    public void consume(SystemMetrics systemMetrics) {
        if (systemMetrics.getId() == null || systemMetrics.getId().isEmpty()) {
            systemMetrics.setId(UUID.randomUUID().toString());
        }
        System.out.println("📥 Received Metrics from Kafka: " + systemMetrics);
        logStorageRepository.save(systemMetrics);
    }
}

