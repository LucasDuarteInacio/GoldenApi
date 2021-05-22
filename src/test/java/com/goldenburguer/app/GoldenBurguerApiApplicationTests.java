package com.goldenburguer.app;

import static com.goldenburguer.app.helpers.ApiConstants.DATE_FORMATTER;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoldenBurguerApiApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  void testing() {

    // LocalDateTime timeA = LocalDateTime.parse("2021-02-06T11:15:38.393", DATE_FORMATTER); verify
    // why this line does not work
    LocalDateTime time = LocalDateTime.now();
    String timea = LocalDateTime.now().format(DATE_FORMATTER);
    System.out.println(time);
    // System.out.println(timeA);
    System.out.println(timea);
  }
}
