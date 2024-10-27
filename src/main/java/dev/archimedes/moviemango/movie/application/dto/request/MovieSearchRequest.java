package dev.archimedes.moviemango.movie.application.dto.request;

import dev.archimedes.moviemango.movie.application.search.FetchType;
import dev.archimedes.moviemango.movie.application.dto.FilterUnit;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.Assert;

import java.util.List;

@Schema(description = "Request object for searching movies with various filters and fetch types.")
public record MovieSearchRequest(
    @Schema(description = "Title of the movie to search for. Required if fetchType is TITLE.")
    String title,

    @Schema(description = "Required if fetchType is CATEGORICAL.")
    FilterUnit category,

    @Schema(description = "List of filters to apply. Required if fetchType is FILTERED.")
    List<FilterUnit> filters,

    @Schema(description = "Type of fetch operation to perform. Must be provided.")
    FetchType fetchType,

    @Schema(description = "Limit the number of results returned.")
    Integer limit
) {

  public MovieSearchRequest {

    Assert.notNull(
        fetchType, "Fetch type is must for processing the search request."
    );

    switch (fetchType) {
      case FILTERED -> Assert.state(
          filters.size() > 1,
          "For filtered request more than one filter should be provided."
      );

      case CATEGORICAL -> Assert.notNull(
          category,
          "At least one category should be provided for categorical result."
      );

      case TITLE -> Assert.isTrue(
          StringUtils.isNotBlank(title),
          "Title should be provided for search with title."
      );
    }
  }

}
