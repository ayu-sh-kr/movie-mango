package dev.archimedes.moviemango.movie.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.archimedes.moviemango.movie.domain.Movie;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieActionResponse(
    @JsonProperty("movie_id")
    Long id,
    String title,
    String description,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate release,
    Double duration,
    String genre,
    String director,
    Double rating,
    String language,
    String origin,

    @JsonFormat(
        pattern = "yyyy-MM-dd hh:MM:ss"
    )
    LocalDateTime createdAt,

    @JsonFormat(
        pattern = "yyyy-MM-dd hh:MM:ss"
    )
    LocalDateTime updatedAt
) {


  public MovieActionResponse(
      Movie movie
  ) {
    this(
        movie.getId(), movie.getTitle().value(), movie.getDescription().value(), movie.getRelease(),
        movie.getDuration(), movie.getGenre().value().value, movie.getDirector(), movie.getRating().value(),
        movie.getLanguage().value(), movie.getOrigin().value(), movie.createdAt, movie.updatedAt
    );
  }

}
