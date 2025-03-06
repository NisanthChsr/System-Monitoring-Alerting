package com.monitoring.system_monitor.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.monitoring.shared_resource.shared.SystemMetrics;

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
