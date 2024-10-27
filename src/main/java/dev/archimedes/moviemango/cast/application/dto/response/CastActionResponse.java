package dev.archimedes.moviemango.cast.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.archimedes.moviemango.cast.domain.Cast;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CastActionResponse(

    @JsonProperty("cast_id")
    Long id,
    String name,
    Integer age,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate dob,
    String gender,
    String country,

    @JsonProperty("created_at")
    LocalDateTime createdAt,

    @JsonProperty("updated_at")
    LocalDateTime updatedAt
) {

  public CastActionResponse(Cast cast) {
    this(
        cast.getId(), cast.getName().value(), cast.getAge().value(), cast.getDob().value(),
        cast.getGender().getValue(), cast.getCountry().value(), cast.getCreatedAt(), cast.getUpdatedAt()
    );
  }

}
