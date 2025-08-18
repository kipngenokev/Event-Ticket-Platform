package com.kipngeno.tickets.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {
    
    @Schema(description = "Error message", example = "Validation failed")
    private String message;
    
    @Schema(description = "HTTP status code", example = "400")
    private int status;
    
    @Schema(description = "Timestamp of the error", example = "2025-08-18T10:15:30")
    private LocalDateTime timestamp;
    
    @Schema(description = "Request path where error occurred", example = "/api/v1/events")
    private String path;
    
    @Schema(description = "List of field errors (for validation errors)")
    private List<FieldError> errors;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "Field-specific error details")
    public static class FieldError {
        @Schema(description = "Field name", example = "name")
        private String field;
        
        @Schema(description = "Error message for the field", example = "Event name is required")
        private String message;
    }
    
    // Constructor for simple error messages
    public ErrorResponse(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}