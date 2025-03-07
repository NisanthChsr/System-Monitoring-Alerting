package com.monitoring.log_storing.controller;

import com.monitoring.log_storing.LogService;
import com.monitoring.log_storing.entity.SystemMetricsDocument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<SystemMetricsDocument> findAll() {
        return logService.findAll();
    }
}
