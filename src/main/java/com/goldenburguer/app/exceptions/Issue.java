package com.goldenburguer.app.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty private final int code;
  @JsonProperty private final String message;
  @JsonProperty private List<String> details;

  public Issue(final IssueEnum issue) {
    this.code = issue.getCode();
    this.message = issue.getMessage();
  }

  public Issue(final IssueEnum issue, final Object... args) {
    code = issue.getCode();
    message = issue.getFormattedMessage(args);
  }

  public Issue(final IssueEnum issue, final List<String> details) {
    this(issue);
    this.details = details;
  }

  public Issue(final IssueEnum issue, final List<String> details, final Object... args) {
    this.code = issue.getCode();
    this.details = details;
    this.message = issue.getFormattedMessage(args);
  }
}
