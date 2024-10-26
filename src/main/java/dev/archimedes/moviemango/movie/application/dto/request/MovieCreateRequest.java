package dev.archimedes.moviemango.movie.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record MovieCreateRequest(
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
    String origin
) {
}
