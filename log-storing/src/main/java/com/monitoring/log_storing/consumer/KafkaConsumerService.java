package com.monitoring.log_storing.consumer;

import com.monitoring.log_storing.entity.SystemMetricsDocument;
import com.monitoring.shared_resource.shared.SystemMetrics;
import com.monitoring.log_storing.repository.LogStorageRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final LogStorageRepository logStorageRepository;
    private final KafkaConsumer<String, SystemMetrics> consumer;

    public KafkaConsumerService(
            LogStorageRepository logStorageRepository,
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${spring.kafka.consumer.group-id}") String groupId) {

        this.logStorageRepository = logStorageRepository;

        // ✅ Explicitly define consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put("spring.json.trusted.packages", "*");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");
        props.put("fetch.min.bytes", "1048576");
        props.put("fetch.max.wait.ms", "5000");
        props.put("max.poll.records", "10");
        props.put("max.poll.interval.ms", "10000");

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("system-metrics"));
        logger.info("Kafka Consumer Service Initialized");
    }

    @Scheduled(fixedRate = 10000)
    public void fetchMetrics() {
        ConsumerRecords<String, SystemMetrics> records = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<String, SystemMetrics> record : records) {
            SystemMetrics systemMetrics = record.value();
            logger.info("📥 Received Metrics: " + systemMetrics);
            SystemMetricsDocument doc = SystemMetricsDocument.from(systemMetrics);
            logStorageRepository.save(doc);
        }
        consumer.commitSync();
    }
}
