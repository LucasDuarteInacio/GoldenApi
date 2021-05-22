package com.goldenburguer.app.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardError {

  private String message;
  private Long timesTamp;
  private String path;
}
