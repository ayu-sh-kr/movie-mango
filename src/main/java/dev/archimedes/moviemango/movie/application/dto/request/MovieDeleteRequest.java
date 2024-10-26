package dev.archimedes.moviemango.movie.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDeleteRequest(
    @JsonProperty("movie_id")
    Long id
) {
}
