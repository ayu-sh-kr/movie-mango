package dev.archimedes.moviemango.moviecast.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class MovieCast {

  private Long id;
  private CastId castId;
  private MovieId movieId;
  private CastRole role;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public MovieCast(Long castId, Long movieId, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.castId = new CastId(castId);
    this.movieId = new MovieId(movieId);
    this.role = new CastRole(role);

    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
