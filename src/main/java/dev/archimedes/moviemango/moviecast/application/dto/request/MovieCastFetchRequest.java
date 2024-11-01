package dev.archimedes.moviemango.moviecast.application.dto.request;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieCastFetchRequest(
    Long id,
    String name,
    MovieCastFetchType type
) {

  public MovieCastFetchRequest {
    Assert.notNull(type, "Fetch type for movie-cast is must.");

    switch (type) {
      case BY_ID -> Assert.notNull(id, "ID can't be null for fetch by id");
      case BY_NAME -> Assert.isTrue(StringUtils.isNotBlank(name), "Name can't be null for fetch by name");
    }

  }

}
