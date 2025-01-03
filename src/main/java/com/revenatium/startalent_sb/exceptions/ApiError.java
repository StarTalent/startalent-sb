package com.revenatium.startalent_sb.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(String message, List<String> details, String path, String errorCode, LocalDateTime timestamp) {
    public ApiError(String message, List<String> details, String path, String errorCode) {
        this(message, details, path, errorCode, LocalDateTime.now());
    }
}
