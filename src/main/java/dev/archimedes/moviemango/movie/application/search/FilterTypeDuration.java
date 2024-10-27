package dev.archimedes.moviemango.movie.application.search;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum FilterTypeDuration {
  DURATION_EQUAL_TO("duration"), DURATION_GREATER_THAN("greater than"), DURATION_LESS_THAN("less than");

  public final String value;

  FilterTypeDuration(String value) {
    this.value = value;
  }

  public static FilterTypeDuration fromString(String value) {
    Assert.isTrue(
        StringUtils.isNotBlank(value),
        "Filter type duration can't be null or blank for conversion"
    );

    for (FilterTypeDuration filterTypeDuration: FilterTypeDuration.values()) {
      if (filterTypeDuration.value.equalsIgnoreCase(value.trim())) {
        return filterTypeDuration;
      }
    }

    throw new IllegalArgumentException("No equivalent found for given filter type duration: " + value);
  }

}
