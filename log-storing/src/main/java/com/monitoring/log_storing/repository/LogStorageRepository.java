package com.monitoring.log_storing.repository;

import com.monitoring.log_storing.entity.SystemMetricsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogStorageRepository extends ElasticsearchRepository<SystemMetricsDocument, String> {
    List<SystemMetricsDocument> findByServer(String server);
    List<SystemMetricsDocument> findByTimestamp(String timestamp);
}
