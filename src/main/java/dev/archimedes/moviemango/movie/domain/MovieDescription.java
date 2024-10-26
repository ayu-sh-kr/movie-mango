package dev.archimedes.moviemango.movie.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieDescription(
    String value
) {

  public MovieDescription {
    Assert.isTrue(StringUtils.isNotBlank(value), "Movie description can't be null or blank.");
    Assert.isTrue(value.split("\\s+").length >= 50, "Movie description must contain at least 50 words.");
  }

}
