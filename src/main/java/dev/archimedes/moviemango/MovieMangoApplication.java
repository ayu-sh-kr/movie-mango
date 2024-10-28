package dev.archimedes.moviemango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MovieMangoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MovieMangoApplication.class, args);
  }

}
