package com.monitoring.log_storing;

import com.monitoring.log_storing.entity.SystemMetrics;
import com.monitoring.log_storing.repository.LogStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LogService {
    private final LogStorageRepository logStorageRepository;

    public LogService(LogStorageRepository logStorageRepository) {
        this.logStorageRepository = logStorageRepository;
    }

    public List<SystemMetrics> findAll() {
        Iterable<SystemMetrics> iterable = logStorageRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
