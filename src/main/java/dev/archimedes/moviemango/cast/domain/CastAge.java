package dev.archimedes.moviemango.cast.domain;

import org.springframework.util.Assert;

public record CastAge(
    Integer value
) {

  public CastAge {
    Assert.notNull(value, "Age of the cast can't be null");
  }

}
