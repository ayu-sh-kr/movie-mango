package dev.archimedes.moviemango.user.application.dtos.request;

import org.springframework.util.Assert;

public record AccountCreateRequest(
    String email,
    String password
) {

  public AccountCreateRequest {
    Assert.notNull(email, "Email is required for account creation");
    Assert.notNull(password, "Password is required for account creation");
  }

}
