package dev.archimedes.moviemango.movie.application.dto;

import dev.archimedes.moviemango.movie.application.search.FilterType;
import dev.archimedes.moviemango.movie.application.search.meta.FilterByDurationMeta;
import dev.archimedes.moviemango.movie.application.search.meta.FilterByRatingMeta;
import dev.archimedes.moviemango.movie.application.search.meta.FilterByReleaseMeta;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.Assert;

@Schema(description = "Sole unit to apply for filtering / categorising")
public record FilterUnit(

    @Schema(description = "Required if filter type is other than *RATING*, *DURATION* and *RELEASE*.")
    String value,

    @Schema(description = "Required if filter type is RELEASE.")
    FilterByReleaseMeta releaseMeta,

    @Schema(description = "Required if filter type is RATING.")
    FilterByRatingMeta ratingMeta,

    @Schema(description = "Required if filter type is DURATION")
    FilterByDurationMeta durationMeta,

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
          ratingMeta, "Rating meta can't be null for categorising / filtering using rating"
      );

      case RELEASE -> Assert.notNull(
          releaseMeta, "Release meta can't be null for categorising / filtering using rating"
      );

      case DURATION -> Assert.notNull(
          durationMeta, "Duration meta can't be null for categorising / filtering using duration"
      );
    }

  }

}
