package com.monitoring.system_monitor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetrics {
    private String timestamp;
    private String server;
    private double cpuUsage;
    private double memoryUsage;
    private double diskUsage;
}
