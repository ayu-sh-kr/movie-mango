package dev.archimedes.moviemango.movie.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
      MovieTitle title, String description, LocalDate release, Double duration,
      String genre, String director, Double rating, String language, String origin,
      LocalDateTime createdAt, LocalDateTime updatedAt
  ) {
    this.title = title;
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
}
