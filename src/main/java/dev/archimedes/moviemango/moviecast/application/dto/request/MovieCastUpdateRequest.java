package dev.archimedes.moviemango.moviecast.application.dto.request;

public record MovieCastUpdateRequest(
    Long id,
    Long castId,
    Long movieId,
    String role
) {

}
