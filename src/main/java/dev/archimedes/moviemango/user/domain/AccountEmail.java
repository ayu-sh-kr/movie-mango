package dev.archimedes.moviemango.user.domain;


import dev.archimedes.moviemango.application.ApplicationUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record AccountEmail(String value) {
  public AccountEmail {
    Assert.state(StringUtils.isNotBlank(value), "Email can't be null or blank");
    Assert.state(ApplicationUtils.isValidEmail(value), "Value should be a valid email");
  }
}
