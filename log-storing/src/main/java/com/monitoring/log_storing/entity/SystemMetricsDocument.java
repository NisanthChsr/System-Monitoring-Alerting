package com.monitoring.log_storing.entity;

import com.monitoring.shared_resource.shared.SystemMetrics;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "system-metrics")
public class SystemMetricsDocument {

    @Id
    private String id;

    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SystemMetricsDocument() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    private String server;

    public SystemMetricsDocument( String id, String timestamp, String server, double cpuUsage, double memoryUsage, double diskUsage) {
        this.timestamp = timestamp;
        this.id = id;
        this.server = server;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
    }
    private double cpuUsage;
    private double memoryUsage;
    private double diskUsage;


    public static SystemMetricsDocument from(SystemMetrics metrics) {
        SystemMetricsDocument doc = new SystemMetricsDocument();
        doc.setId(metrics.getId());
        doc.setCpuUsage(metrics.getCpuUsage());
        doc.setServer(metrics.getServer());
        doc.setTimestamp(metrics.getTimestamp());
        doc.setMemoryUsage(metrics.getMemoryUsage());
        doc.setDiskUsage(metrics.getDiskUsage());
        return doc;
    }

    // getters and setters
}
