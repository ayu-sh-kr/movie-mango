package dev.archimedes.moviemango.profile.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record Street(
    String value
) {

  public Street {
    Assert.state(StringUtils.isNotBlank(value), "Street can't be null or blank");
  }

}
