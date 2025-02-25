package com.monitoring.system_monitor.producer;

import com.monitoring.system_monitor.entity.SystemMetrics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SystemMetricsProducer {

    private final KafkaTemplate<String, SystemMetrics> kafkaTemplate;

    public SystemMetricsProducer(KafkaTemplate<String, SystemMetrics> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMetrics(SystemMetrics metrics) {
        kafkaTemplate.send("system-metrics", metrics.getServer(), metrics);
        System.out.println("✅ Sent metrics to Kafka: " + metrics);
    }
}
