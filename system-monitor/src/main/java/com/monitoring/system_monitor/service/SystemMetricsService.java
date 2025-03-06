package com.monitoring.system_monitor.service;

import com.monitoring.shared_resource.shared.SystemMetrics;
import com.monitoring.system_monitor.producer.SystemMetricsProducer;
import org.springframework.scheduling.annotation.Scheduled;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Instant;

@Service
public class SystemMetricsService {

    private final SystemInfo systemInfo = new SystemInfo();
    private final SystemMetricsProducer metricsProducer;

    public SystemMetricsService(SystemMetricsProducer metricsProducer) {
        this.metricsProducer = metricsProducer;
    }
    @Scheduled(fixedRate = 10000) // Every 5 seconds
    public void collectAndSendMetrics() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        GlobalMemory memory = systemInfo.getHardware().getMemory();

        double[] cpuLoad = processor.getSystemLoadAverage(3);
        double oneMinuteLoad = cpuLoad[0];
        long totalMemory = memory.getTotal();
        long usedMemory = totalMemory - memory.getAvailable();
        double memoryUsage = ((double) usedMemory / totalMemory) * 100;

        File diskPartition = new File("/");
        long totalDiskSpace = diskPartition.getTotalSpace();
        long freeDiskSpace = diskPartition.getFreeSpace();
        double diskUsage = ((double) (totalDiskSpace - freeDiskSpace) / totalDiskSpace) * 100;

        SystemMetrics metrics = new SystemMetrics(
                Instant.now().toString(),
                "server-1",
                oneMinuteLoad,
                memoryUsage,
                diskUsage
        );

        metricsProducer.sendMetrics(metrics);
    }

    public SystemMetrics getLatestMetrics() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        GlobalMemory memory = systemInfo.getHardware().getMemory();

        double[] cpuLoad = processor.getSystemLoadAverage(3);
        double oneMinuteLoad = cpuLoad[0];
        long totalMemory = memory.getTotal();
        long usedMemory = totalMemory - memory.getAvailable();
        double memoryUsage = ((double) usedMemory / totalMemory) * 100;

        File diskPartition = new File("/");
        long totalDiskSpace = diskPartition.getTotalSpace();
        long freeDiskSpace = diskPartition.getFreeSpace();
        double diskUsage = ((double) (totalDiskSpace - freeDiskSpace) / totalDiskSpace) * 100;

        return new SystemMetrics(
                Instant.now().toString(),
                "server-1",
                oneMinuteLoad,
                memoryUsage,
                diskUsage
        );
    }
}
