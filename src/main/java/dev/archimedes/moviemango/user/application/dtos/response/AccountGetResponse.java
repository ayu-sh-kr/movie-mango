package dev.archimedes.moviemango.user.application.dtos.response;

import dev.archimedes.moviemango.user.domain.Account;

public record AccountGetResponse(
    Long id,
    String email
) {

  public AccountGetResponse(Account account) {
    this(account.getId(), account.getEmail().value());
  }

}
