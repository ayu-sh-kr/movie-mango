package dev.archimedes.moviemango.profile.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum AvailableGender {
  MALE("male"), FEMALE("female"), OTHER("other");

  public final String value;

  AvailableGender(String value) {
    this.value = value;
  }

  public static AvailableGender fromString(String value) {
    Assert.isTrue(StringUtils.isNotBlank(value), "Gender can't be null or blank");
    for (AvailableGender gender: AvailableGender.values()) {
      if(gender.value.equalsIgnoreCase(value)) {
        return gender;
      }
    }

    throw new IllegalArgumentException("Not a valid gender. Choose from [male, female, other]");
  }
}
