package dev.archimedes.moviemango.moviecast.application.dto.response;

import dev.archimedes.moviemango.moviecast.domain.MovieCast;

import java.time.LocalDateTime;

public record MovieCastActionResponse(
    Long id,
    Long castId,
    Long movieId,
    String role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public MovieCastActionResponse(MovieCast movieCast) {
    this(
        movieCast.getId(), movieCast.getCastId(), movieCast.getMovieId(), movieCast.getRole(),
        movieCast.getCreatedAt(), movieCast.getUpdatedAt()
    );
  }

}
