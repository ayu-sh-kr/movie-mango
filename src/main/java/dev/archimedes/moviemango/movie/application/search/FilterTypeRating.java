package dev.archimedes.moviemango.movie.application.search;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum FilterTypeRating {
  RATING_EQUAL_TO("equal"), RATING_GREATER_THAN("greater than"), RATING_LESS_THAN("less than");

  public final String value;

  FilterTypeRating(String value) {
    this.value = value;
  }

  public static FilterTypeRating fromString(String value) {
    Assert.isTrue(
        StringUtils.isNotBlank(value),
        "Filter type rating can't be null or blank for conversion"
    );

    for (FilterTypeRating filterTypeRating: FilterTypeRating.values()) {
      if (filterTypeRating.value.equalsIgnoreCase(value.trim())) {
        return filterTypeRating;
      }
    }

    throw new IllegalArgumentException("No equivalent found for given filter type rating: " + value);
  }
}
