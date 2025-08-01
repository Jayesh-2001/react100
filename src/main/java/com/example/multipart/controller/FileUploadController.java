package com.example.multipart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Validate file
            if (file.isEmpty()) {
                response.put("error", "File is empty");
                return ResponseEntity.badRequest().body(response);
            }

            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save file
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // Prepare response
            response.put("message", "File uploaded successfully");
            response.put("fileName", fileName);
            response.put("fileSize", file.getSize());
            response.put("contentType", file.getContentType());
            response.put("name", name);
            response.put("description", description);
            response.put("filePath", filePath.toString());

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "Failed to upload file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<Map<String, Object>> handleMultipleFileUpload(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("category") String category,
            @RequestParam(value = "tags", required = false) String tags) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Map<String, Object> uploadedFiles = new HashMap<>();

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(file.getInputStream(), filePath);

                    Map<String, Object> fileInfo = new HashMap<>();
                    fileInfo.put("size", file.getSize());
                    fileInfo.put("contentType", file.getContentType());
                    fileInfo.put("path", filePath.toString());

                    uploadedFiles.put(fileName, fileInfo);
                }
            }

            response.put("message", "Files uploaded successfully");
            response.put("category", category);
            response.put("tags", tags);
            response.put("uploadedFiles", uploadedFiles);
            response.put("totalFiles", uploadedFiles.size());

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "Failed to upload files: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/form-data")
    public ResponseEntity<Map<String, Object>> handleFormData(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "priority", defaultValue = "medium") String priority,
            @RequestParam(value = "isPublic", defaultValue = "false") boolean isPublic) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Handle optional file upload
            if (file != null && !file.isEmpty()) {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                response.put("fileName", fileName);
                response.put("fileSize", file.getSize());
                response.put("contentType", file.getContentType());
            }

            // Process form data
            response.put("message", "Form data processed successfully");
            response.put("title", title);
            response.put("content", content);
            response.put("priority", priority);
            response.put("isPublic", isPublic);
            response.put("timestamp", System.currentTimeMillis());

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "Failed to process form data: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}