package dev.archimedes.moviemango.movie.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.time.LocalDate;

public record MovieUpdateRequest(

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
    String origin
) {

    public MovieUpdateRequest {
        Assert.notNull(
            id,
            "Id is required to perform the update."
        );
    }

}
