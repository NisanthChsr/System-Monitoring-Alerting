package com.monitoring.system_monitor.controller;

import com.monitoring.system_monitor.entity.SystemMetrics;
import com.monitoring.system_monitor.service.SystemMetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class SystemMetricsController {
    private final SystemMetricsService systemMetricsService;

    public SystemMetricsController(SystemMetricsService systemMetricsService) {
        this.systemMetricsService = systemMetricsService;
    }

    @GetMapping("/latest")
    public SystemMetrics getLatestMetrics() {
        return systemMetricsService.getLatestMetrics();
    }
}
