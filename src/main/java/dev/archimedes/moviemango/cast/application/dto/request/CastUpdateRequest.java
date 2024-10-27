package dev.archimedes.moviemango.cast.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.time.LocalDate;

public record CastUpdateRequest(

    @JsonProperty("cast_id")
    Long id,
    String name,
    Integer age,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate dob,
    String gender,
    String country
) {

    public CastUpdateRequest {
        Assert.notNull(
            id,
            "Id is required to perform the update."
        );
    }

}
