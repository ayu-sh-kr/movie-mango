package dev.archimedes.moviemango.moviecast.domain;

import org.springframework.util.Assert;

public record CastId(
    Long value
) {

  public CastId {
    Assert.notNull(
        value,
        "Cast Id can't be null for movie-cast relation"
    );
  }

}
