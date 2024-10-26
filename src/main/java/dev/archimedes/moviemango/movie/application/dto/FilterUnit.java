package dev.archimedes.moviemango.movie.application.dto;

import dev.archimedes.moviemango.movie.application.FilterType;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Schema(description = "Sole unit to apply for filtering / categorising")
public record FilterUnit(

    @Schema(description = "Required if filter type is other than *RATING*, *DURATION* and *RELEASE*.")
    String value,

    @Schema(description = "Required if filter type is RELEASE.")
    LocalDate release,

    @Schema(description = "Required if filter type is RATING.")
    Double rating,

    @Schema(description = "Required if filter type is DURATION")
    Double duration,

    @Schema(description = "Required for filtering out result")
    FilterType type
) {

  public FilterUnit {

    Assert.notNull(
        type,
        "Filter type can't be null in Filter Unit"
    );

    switch (type) {
      case GENRE, DIRECTOR, LANGUAGE, ORIGIN, CAST ->
        Assert.isTrue(
            StringUtils.isNotBlank(value),
            "Value can't be null for categorising / filtering for given type"
        );

      case RATING -> Assert.notNull(
          rating, "Rating can't be null for categorising / filtering using rating"
      );

      case DURATION -> Assert.notNull(
          duration,
          "Duration can't be null for categorising / filtering using duration"
      );
    }

  }

}
