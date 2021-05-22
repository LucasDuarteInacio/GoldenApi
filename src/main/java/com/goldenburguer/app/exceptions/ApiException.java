package com.goldenburguer.app.exceptions;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

  private String detail;
  private Integer status;
  private Issue issue;

  public ApiException(String message, String detail, Integer status) {
    super(message);
    this.detail = detail;
    this.status = status;
  }

  public ApiException(final Issue issue) {
    this.issue = issue;
  }

  public ApiException(final Issue issue, final Throwable cause) {
    super(issue.getMessage(), cause);
    this.issue = issue;
  }
}
