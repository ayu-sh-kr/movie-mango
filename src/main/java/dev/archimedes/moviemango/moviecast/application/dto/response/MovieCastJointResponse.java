package dev.archimedes.moviemango.moviecast.application.dto.response;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public record MovieCastJointResponse(
    String name,
    String character,
    String role,
    String movie
) {

  public MovieCastJointResponse {
    Assert.isTrue(
        StringUtils.isNotBlank(name), "Name of cast can't be blank."
    );

    Assert.isTrue(
        StringUtils.isNotBlank(character), "Character name of cast can't be blank."
    );

    Assert.isTrue(
        StringUtils.isNotBlank(role), "Role of cast can't be blank."
    );

    Assert.isTrue(
        StringUtils.isNotBlank(movie), "Movie name can't be blank."
    );
  }

}
