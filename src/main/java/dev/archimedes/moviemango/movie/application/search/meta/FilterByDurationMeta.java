package dev.archimedes.moviemango.movie.application.search.meta;

import dev.archimedes.moviemango.movie.application.search.FilterTypeDuration;
import org.springframework.util.Assert;

public record FilterByDurationMeta(
    String type,
    Double duration
) {

  public FilterByDurationMeta {
    FilterTypeDuration.fromString(type);
    Assert.notNull(duration, "Duration can't be null for filter by duration");
  }
}
