package dev.archimedes.moviemango.movie.application.search.meta;

import dev.archimedes.moviemango.movie.application.search.FilterTypeRating;
import org.springframework.util.Assert;

public record FilterByRatingMeta(
    String type,
    Double rating
) {

  public FilterByRatingMeta {
    FilterTypeRating.fromString(type);
    Assert.notNull(
        rating,
        "Rating can't be null for filter by rating"
    );
  }

}
