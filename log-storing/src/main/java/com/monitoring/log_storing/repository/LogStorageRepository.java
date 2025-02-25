package com.monitoring.log_storing.repository;

import com.monitoring.log_storing.entity.SystemMetrics;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogStorageRepository extends ElasticsearchRepository<SystemMetrics, String> {
    List<SystemMetrics> findByServer(String server);
    List<SystemMetrics> findByTimestamp(String timestamp);
}
