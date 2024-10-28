package dev.archimedes.moviemango.user.domain;


import dev.archimedes.moviemango.application.ApplicationUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

/// **AccountEmail** is record to validate and manage state of the email field in the [Account].
///
///   Perform validation for null and blank email value.
///  Throws [IllegalStateException] if the validation fails
///
/// @param value
///
/// @see dev.archimedes.moviemango.user.domain.Account
public record AccountEmail(String value) {
  public AccountEmail {
    Assert.state(StringUtils.isNotBlank(value), "Email can't be null or blank");
    Assert.state(ApplicationUtils.isValidEmail(value), "Value should be a valid email");
  }
}
