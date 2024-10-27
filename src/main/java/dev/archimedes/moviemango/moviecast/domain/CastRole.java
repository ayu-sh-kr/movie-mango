package dev.archimedes.moviemango.moviecast.domain;

public record CastRole(
    Role role
) {

  public CastRole(String value) {
    this(
        Role.fromString(value)
    );
  }

}
