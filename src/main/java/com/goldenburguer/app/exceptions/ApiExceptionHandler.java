package com.goldenburguer.app.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected StandardError processNotFoundException(
      final ApiException ex, final WebRequest request) {
    // LOGGER.error(request.getHeader(ApiConstants.REQUEST_TRACE_ID_HEADER), ex);
    return StandardError.builder()
        .message(ex.getIssue().getMessage())
        .timesTamp(System.currentTimeMillis())
        .path(request.getDescription(true))
        .build();
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  protected StandardError processBadRequestException(
      final ApiException ex, final WebRequest request) {
    return StandardError.builder()
        .message(ex.getIssue().getMessage())
        .timesTamp(System.currentTimeMillis())
        .path(request.getDescription(true))
        .build();
  }
}
