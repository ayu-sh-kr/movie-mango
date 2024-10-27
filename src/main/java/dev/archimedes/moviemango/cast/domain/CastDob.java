package dev.archimedes.moviemango.cast.domain;

import org.springframework.util.Assert;

import java.time.LocalDate;

public record CastDob(
    LocalDate value
) {

  public CastDob {
    Assert.notNull(
        value,
        "Dob can be a null"
    );
  }

}
