package dev.archimedes.moviemango.movie.application.dto.request;

import org.springframework.util.Assert;

public record MovieCheckRequest(Long movieId) {

  public MovieCheckRequest {
    Assert.notNull(movieId, "");
  }

}
