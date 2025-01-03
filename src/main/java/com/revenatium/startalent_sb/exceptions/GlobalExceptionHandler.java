package com.revenatium.startalent_sb.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static java.util.Collections.emptyList;

@ControllerAdvice
public class GlobalExceptionHandler  {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorizedException(
        UnauthorizedException ex,
        HttpServletRequest request) {
        log.error("UnauthorizedException: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(
            "Acceso no autorizado",
            List.of("No tienes permisos para acceder a esta ruta"),
            request.getRequestURI(),
            "UNAUTHORIZED"
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException(
        EmailAlreadyExistsException ex,
        HttpServletRequest request) {
        log.error("EmailAlreadyExistsException: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(
            "El email ya está registrado",
            emptyList(),
            request.getRequestURI(),
            "EMAIL_EXISTS"
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(
        UserNotFoundException ex,
        HttpServletRequest request) {
        log.error("UserNotFoundException: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(
            "Usuario no encontrado",
            emptyList(),
            request.getRequestURI(),
            "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Manejador para excepciones genéricas no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
        Exception ex,
        HttpServletRequest request) {
        log.error("Exception: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(
            "Error interno del servidor",
            emptyList(),
            request.getRequestURI(),
            "INTERNAL_ERROR"
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(
        EntityNotFoundException ex,
        HttpServletRequest request) {
        log.error("EntityNotFoundException: {}", ex.getMessage(), ex);
        List<String> errorDetails = List.of(ex.getMessage());
        ApiError apiError = new ApiError(
            "No se encontró el recurso",
            errorDetails,
            request.getRequestURI(),
            "ENTITY_NOT_FOUND"
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
        MethodArgumentNotValidException ex,
        HttpServletRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
        List<String> errorDetails = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

        ApiError apiError = new ApiError(
            "Error de validación",
            errorDetails,
            request.getRequestURI(),
            "VALIDATION_ERROR"
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
