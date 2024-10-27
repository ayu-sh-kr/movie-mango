package dev.archimedes.moviemango.moviecast.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum Role {
  LEAD_ACTOR("lead actor"), SUPPORTING_ACTOR("supporting actor"), DIRECTOR("director"), PRODUCER("producer"),
  WRITER("writer"), CINEMATOGRAPHER("cinematographer"), EDITOR("editor"), COMPOSER("composer");

  public final String value;

  Role(String value) {
    this.value = value;
  }


  public static Role fromString(String value) {
    Assert.isTrue(
        StringUtils.isNotBlank(value),
        "Value can't be null for role conversion"
    );

    for (Role role: Role.values()) {
      if(role.value.equalsIgnoreCase(value)) {
        return role;
      }
    }

    throw new IllegalArgumentException("Value provided is not a valid cast role. [Value: " + value + "]");
  }

}
