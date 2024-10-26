package dev.archimedes.moviemango.profile.domain;

public record Gender(
    AvailableGender value
) {

  public Gender(String value) {
    this(AvailableGender.fromString(value));
  }

  public String getValue() {
    return value.value;
  }
}
