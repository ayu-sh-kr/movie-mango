package dev.archimedes.moviemango.movie.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieOrigin(
    String value
) {

  public MovieOrigin {
    Assert.isTrue(StringUtils.isNotBlank(value), "Movie language can't be null or blank,");
  }

}
