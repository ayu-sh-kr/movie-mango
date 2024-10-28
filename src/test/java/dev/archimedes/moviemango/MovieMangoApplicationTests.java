package dev.archimedes.moviemango;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class MovieMangoApplicationTests {

  @Test
  void contextLoads() {
    ApplicationModules.of(MovieMangoApplication.class).verify();
  }

}
