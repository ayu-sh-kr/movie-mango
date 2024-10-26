package dev.archimedes.moviemango.movie.domain;

public record MovieGenre(
    AvailableGenre value
) {

  public MovieGenre(String value) {
    this(AvailableGenre.fromString(value));
  }

}
