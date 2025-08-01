# Spring Boot Multipart Form Data API

A simple Spring Boot REST API that handles multipart form data including file uploads and text fields.

## Features

- Single file upload with additional form fields
- Multiple file upload support
- Form data processing with optional file attachment
- Configurable file size limits
- Error handling and validation
- JSON response format

## API Endpoints

### 1. Single File Upload
**POST** `/api/upload`

Upload a single file with additional metadata.

**Form Data:**
- `file` (required): The file to upload
- `name` (required): Name field
- `description` (required): Description field

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/upload \
  -F "file=@example.txt" \
  -F "name=Test File" \
  -F "description=This is a test file upload"
```

### 2. Multiple File Upload
**POST** `/api/upload-multiple`

Upload multiple files with category and tags.

**Form Data:**
- `files` (required): Array of files to upload
- `category` (required): Category for the files
- `tags` (optional): Tags for the files

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/upload-multiple \
  -F "files=@file1.txt" \
  -F "files=@file2.pdf" \
  -F "category=documents" \
  -F "tags=important,work"
```

### 3. Form Data Processing
**POST** `/api/form-data`

Process form data with optional file attachment.

**Form Data:**
- `file` (optional): Optional file attachment
- `title` (required): Title field
- `content` (required): Content field
- `priority` (optional, default: "medium"): Priority level
- `isPublic` (optional, default: false): Public visibility flag

**Example using curl:**
```bash
curl -X POST http://localhost:8080/api/form-data \
  -F "file=@attachment.pdf" \
  -F "title=My Form Submission" \
  -F "content=This is the content of my form" \
  -F "priority=high" \
  -F "isPublic=true"
```

## Configuration

The application is configured with the following multipart settings in `application.properties`:

- Maximum file size: 10MB
- Maximum request size: 50MB
- Temporary file location: `/tmp`

## Running the Application

1. Make sure you have Java 17+ and Maven installed
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

## File Storage

Uploaded files are stored in the `uploads/` directory relative to the application's working directory.

## Response Format

All endpoints return JSON responses with the following structure:

**Success Response:**
```json
{
  "message": "File uploaded successfully",
  "fileName": "example.txt",
  "fileSize": 1024,
  "contentType": "text/plain",
  "filePath": "uploads/example.txt",
  "timestamp": 1703123456789
}
```

**Error Response:**
```json
{
  "error": "File is empty"
}
```

## Testing with Postman

1. Create a new POST request
2. Set the URL to one of the endpoints (e.g., `http://localhost:8080/api/upload`)
3. In the Body tab, select "form-data"
4. Add the required fields and select files as needed
5. Send the request

## Dependencies

- Spring Boot 3.2.0
- Spring Boot Web Starter
- Spring Boot Validation Starter
- Java 17