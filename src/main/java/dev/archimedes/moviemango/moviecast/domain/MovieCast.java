package dev.archimedes.moviemango.moviecast.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

  public Long getCastId() {
    return castId.value();
  }

  public Long getMovieId() {
    return movieId.value();
  }

  public String getRole() {
    return role.role().value;
  }
}
