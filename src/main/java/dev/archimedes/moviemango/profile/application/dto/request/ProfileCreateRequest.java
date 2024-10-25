package dev.archimedes.moviemango.profile.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ProfileCreateRequest(
    String name,
    Integer age,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate dob,
    String street,
    String state,
    String zip,
    String country
) {
}
