package com.denizgokce.csvwizard.dto;

import java.util.List;

import com.denizgokce.csvwizard.model.Record;

public class UploadResponse {
    private List<Record> successfulRecords;
    private List<String> failedRecords;

    public UploadResponse(List<Record> successfulRecords, List<String> failedRecords) {
        this.successfulRecords = successfulRecords;
        this.failedRecords = failedRecords;
    }

    public List<Record> getSuccessfulRecords() {
        return successfulRecords;
    }

    public void setSuccessfulRecords(List<Record> successfulRecords) {
        this.successfulRecords = successfulRecords;
    }

    public List<String> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<String> failedRecords) {
        this.failedRecords = failedRecords;
    }
}