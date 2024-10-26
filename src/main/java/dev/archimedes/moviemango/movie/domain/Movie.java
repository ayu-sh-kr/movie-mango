package dev.archimedes.moviemango.movie.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
public class Movie {

  private Long id;
  private MovieTitle title;
  private MovieDescription description;
  private LocalDate release;
  private Double duration;
  private MovieGenre genre;
  private String director;
  private MovieRating rating;
  private MovieLanguage language;
  private MovieOrigin origin;

  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public Movie(
      String title, String description, LocalDate release, Double duration,
      String genre, String director, Double rating, String language, String origin,
      LocalDateTime createdAt, LocalDateTime updatedAt
  ) {
    this.title = new MovieTitle(title);
    this.description = new MovieDescription(description);
    this.release = release;
    this.duration = duration;
    this.genre = new MovieGenre(genre);
    this.director = director;
    this.rating = new MovieRating(rating);
    this.language = new MovieLanguage(language);
    this.origin = new MovieOrigin(origin);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Movie movie = (Movie) o;
    return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(release, movie.release) && Objects.equals(duration, movie.duration) && Objects.equals(genre, movie.genre) && Objects.equals(director, movie.director) && Objects.equals(rating, movie.rating) && Objects.equals(language, movie.language) && Objects.equals(origin, movie.origin) && Objects.equals(createdAt, movie.createdAt) && Objects.equals(updatedAt, movie.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
