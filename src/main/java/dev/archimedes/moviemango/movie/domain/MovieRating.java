package dev.archimedes.moviemango.movie.domain;

import org.springframework.util.Assert;

public record MovieRating(
    Double value
) {

  private static final double RATING_LIMIT = 10;

  public MovieRating {
    Assert.state(value <= RATING_LIMIT, "Rating value should be out of 10");
  }

}
