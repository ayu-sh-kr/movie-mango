package dev.archimedes.moviemango.movie.application.search.meta;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.archimedes.moviemango.movie.application.search.FilterTypeRelease;
import org.springframework.util.Assert;

import java.time.LocalDate;

public record FilterByReleaseMeta(
    String type,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate date
) {

  public FilterByReleaseMeta {
    FilterTypeRelease.fromString(type);
    Assert.notNull(
        date,
        "Date can't be null for filter by release"
    );
  }
}
