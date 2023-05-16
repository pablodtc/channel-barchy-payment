package com.bachy.payment.exception;

import com.bachy.payment.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(Throwable throwable) {
    log.error("Ocurrio un error no controlado", throwable);
    ApiResponse<?> apiResponse = ApiResponse.builder()
            .code("E99")
            .message("Ocurrio un error inesperado")
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(WebExchangeBindException exception) {
    log.error("Ocurrio un error no controlado", exception);
    String fieldError = exception.getBindingResult().getFieldError().getField();
    String defaultMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
    ApiResponse<?> apiResponse = ApiResponse.builder()
            .code("E99")
            .message("The field " + fieldError + " " + defaultMessage)
            .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
/*
  @ExceptionHandler(TransferException.class)
  public ResponseEntity<ApiResponse<?>> handleTransferException(TransferException transferException) {
    log.info("Transferencia fallida por {} - {}", transferException.getCode(), transferException.getMessage());
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code(transferException.getCode())
      .message(transferException.getMessage())
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleValidations(MethodArgumentNotValidException validationException) {

    List<FieldValidationError> fieldValidationErrors = validationException.getBindingResult().getFieldErrors()
      .stream()
      .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
      .collect(Collectors.toList());

    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("E10")
      .message("Error de validación")
      .fieldErrors(fieldValidationErrors)
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException unauthorizedException) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("AU01")
      .message("No está autorizado para consumir este servicio")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UnauthenticatedException.class)
  public ResponseEntity<ApiResponse<?>> handleUnauthenticatedException(UnauthenticatedException unauthenticatedException) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("AU01")
      .message("Primero debe autenticarse")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ApiResponse<?>> handleExpiredJwtException(TokenExpiredException expiredJwtException) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("AU01")
      .message("El JWT está expirado")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(DatabaseException databaseException) {
    log.error("Ocurrio un error de base de datos", databaseException);
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("DB01")
      .message(databaseException.getMessage())
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(Throwable throwable) {
    log.error("Ocurrio un error no controlado", throwable);
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("E99")
      .message("Ocurrio un error inesperado")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ApiResponse<?>> dataAccessException(EmptyResultDataAccessException throwable) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("X01")
      .message("no existen registros")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
  public ResponseEntity<ApiResponse<?>> incorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException throwable) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("X01")
      .message("Cantidad de registros incorrectos")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
*/
}