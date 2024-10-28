package dev.archimedes.moviemango.user.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

/// **Account Password** is record for handling the validation of the password field in account and hold the
/// state of the password.
///
/// The main responsibility of this record is to validate the state of password field for null or blank value.
/// Also check for the character length, whether it's valid or not.
///
/// Throws [IllegalStateException] if the validation fails.
///
/// @param value
///
/// @see dev.archimedes.moviemango.user.domain.Account
public record AccountPassword(
    String value
) {
  public AccountPassword {
    Assert.state(StringUtils.isNotBlank(value), "Password can't be blank");
    Assert.state(value.length() >= 8, "Minimum password length is 8 character");
  }
}
