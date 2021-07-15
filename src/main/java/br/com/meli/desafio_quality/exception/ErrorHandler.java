package br.com.meli.desafio_quality.exception;

import br.com.meli.desafio_quality.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorDTO> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
    ErrorDTO error = new ErrorDTO();
    error.setName("Method not valid exception.");
    List<String> listFields = exception.getFieldErrors().stream().map(e -> e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
    error.setMessage("Invalid Fields: " + listFields.toString());
    error.setStatusCode(400);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<ErrorDTO> HttpMessageNotReadableExceptionHandler(Exception exception) {
    ErrorDTO error = new ErrorDTO();
    error.setName("Http message Not Readable Exception.");
    error.setMessage("Invalid request body");
    error.setStatusCode(400);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({HouseNotFoundException.class})
  public ResponseEntity<ErrorDTO> HouseNotFoundExceptionHandler(Exception exception) {
    ErrorDTO error = new ErrorDTO();
    error.setName("House not found exception");
    error.setMessage(exception.getMessage());
    error.setStatusCode(400);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({DistrictNotFoundException.class})
  public ResponseEntity<ErrorDTO> DistrictNotFoundExceptionHandler(Exception exception) {
    ErrorDTO error = new ErrorDTO();
    error.setName("District not found exception");
    error.setMessage(exception.getMessage());
    error.setStatusCode(400);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
