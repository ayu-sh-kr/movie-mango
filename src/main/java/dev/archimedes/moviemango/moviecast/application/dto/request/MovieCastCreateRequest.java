package dev.archimedes.moviemango.moviecast.application.dto.request;

public record MovieCastCreateRequest(
    Long castId,
    Long movieId,
    String role
) {

}
