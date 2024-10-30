package dev.archimedes.moviemango.cast.application.dto.request;

import org.springframework.util.Assert;

public record CastCheckRequest(
    Long castId
) {

  public CastCheckRequest {
    Assert.notNull(castId, "Cast Id must be provided for check request");
  }

}
