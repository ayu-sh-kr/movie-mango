package dev.archimedes.moviemango.user.application.dtos.request;

import org.springframework.util.Assert;

public record AccountDeleteRequest(
    Long id
) {

  public AccountDeleteRequest {
    Assert.notNull(id, "Id can't be null for delete request");
  }

}
