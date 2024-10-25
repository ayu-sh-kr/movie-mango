package dev.archimedes.moviemango.profile.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record Country(
    String value
) {
  public Country {
    Assert.state(StringUtils.isNotBlank(value), "Country can't be null or blank");
  }
}
