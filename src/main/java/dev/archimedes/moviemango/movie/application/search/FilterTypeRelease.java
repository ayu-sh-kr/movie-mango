package dev.archimedes.moviemango.movie.application.search;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum FilterTypeRelease {
  ON_RELEASE("on release"), BEFORE_RELEASE("before release"), AFTER_RELEASE("after release"),
  ON_AND_AFTER_RELEASE("on and after release"), ON_AND_BEFORE_RELEASE("on and before release");

  public final String value;

  FilterTypeRelease(String value) {
    this.value = value;
  }

  public static FilterTypeRelease fromString(String value) {
    Assert.isTrue(
        StringUtils.isNotBlank(value),
        "Filter type release can't be null or blank for conversion."
    );

    for (FilterTypeRelease filterTypeRelease: FilterTypeRelease.values()) {
      if (filterTypeRelease.value.equalsIgnoreCase(value.trim())) {
        return filterTypeRelease;
      }
    }

    throw new IllegalArgumentException("No equivalent found for given filter type release: " + value);
  }


}
