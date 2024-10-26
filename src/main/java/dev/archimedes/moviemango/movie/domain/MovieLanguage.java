package dev.archimedes.moviemango.movie.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieLanguage(
    String value
) {

  public MovieLanguage {
    Assert.isTrue(StringUtils.isNotBlank(value), "Movie language can't be null or blank,");
  }

}
