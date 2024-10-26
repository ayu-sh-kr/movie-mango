package dev.archimedes.moviemango.movie.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieTitle(
    String value
) {

  public MovieTitle {
    Assert.isTrue(StringUtils.isNotBlank(value), "Movie title can't be null");
  }

}
