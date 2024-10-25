package dev.archimedes.moviemango.profile.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record Zip(
    String value
) {
  public Zip {
    Assert.state(StringUtils.isNotBlank(value), "Zip can't be null or blank");
  }
}
