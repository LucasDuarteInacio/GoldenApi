package com.goldenburguer.app.helpers;

import java.time.format.DateTimeFormatter;

public abstract class ApiConstants {

  public static final String VARIABLE = "Edit this value as needed and/or add new ones";

  /** see more on https://blog.cvinicius.com.br/2018/08/utilizando-localdate-localdatetime-e.html */
  public static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public ApiConstants() {}
}
