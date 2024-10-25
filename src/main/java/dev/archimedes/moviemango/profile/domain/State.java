package dev.archimedes.moviemango.profile.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record State(
    String value
) {

  public State {
    Assert.state(StringUtils.isNotBlank(value), "State can't be null or blank");
  }
}
