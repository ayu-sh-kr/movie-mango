package dev.archimedes.moviemango.user.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record AccountPassword(
    String value
) {
  public AccountPassword {
    Assert.state(StringUtils.isNotBlank(value), "Password can't be blank");
    Assert.state(value.length() >= 8, "Minimum password length is 8 character");
  }
}
