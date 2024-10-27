package dev.archimedes.moviemango.moviecast.domain;

import org.springframework.util.Assert;

public record MovieId(
    Long value
) {

  public MovieId {
    Assert.notNull(
        value,
        "Movie Id can't be null for given movie-cast relation"
    );
  }

}
