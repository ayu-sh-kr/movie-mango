package dev.archimedes.moviemango.cast.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record CastName(
    String value
) {

  public CastName {
    Assert.isTrue(
        StringUtils.isNotBlank(value),
        "Name of the cast can't be null or blank."
    );
  }
}
