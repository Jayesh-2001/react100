package com.example.multipart.dto;

import java.time.LocalDateTime;

public class UploadResponse {
    private String message;
    private String fileName;
    private long fileSize;
    private String contentType;
    private String filePath;
    private LocalDateTime timestamp;

    public UploadResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public UploadResponse(String message, String fileName, long fileSize, String contentType, String filePath) {
        this();
        this.message = message;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.filePath = filePath;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}