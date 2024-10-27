package dev.archimedes.moviemango.cast.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public record CastDeleteRequest(

    @JsonProperty("cast_id")
    Long id
) {

  public CastDeleteRequest {
    Assert.notNull(
        id,
        "Id can't be null for delete request"
    );
  }

}
