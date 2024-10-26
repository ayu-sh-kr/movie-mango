package dev.archimedes.moviemango.movie.domain;

import io.micrometer.common.util.StringUtils;
import org.springframework.util.Assert;

public enum AvailableGenre {
  ACTION("action"), ADVENTURE("adventure"), COMEDY("comedy"), DRAMA("drama"),
  FANTASY("fantasy"), HORROR("horror"), MYSTERY("mystery"), ROMANCE("romance"),
  SCI_FI("science fiction"), THRILLER("thriller"), WESTERN("western"), ANIMATION("animation"),
  DOCUMENTARY("documentary"), MUSICAL("musical"), CRIME("crime"), FAMILY("family"),
  HISTORICAL("historical"), WAR("war"), SPORT("sport"), BIOGRAPHY("biography");

  public final String value;

  AvailableGenre(String value) {
    this.value = value;
  }

  public static AvailableGenre fromString(String genre) {
    Assert.isTrue(StringUtils.isNotBlank(genre), "Genre can't be null");
    for (AvailableGenre availableGenre : AvailableGenre.values()) {
      if (availableGenre.value.equalsIgnoreCase(genre)) {
        return availableGenre;
      }
    }
    throw new IllegalArgumentException("Genre not found: " + genre);
  }

}
