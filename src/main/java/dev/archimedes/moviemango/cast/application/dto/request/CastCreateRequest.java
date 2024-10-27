package dev.archimedes.moviemango.cast.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CastCreateRequest(
    String name,
    Integer age,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate dob,
    String gender,
    String country
) {
}
