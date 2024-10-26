package dev.archimedes.moviemango.profile.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.archimedes.moviemango.profile.domain.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfileActionResponse(
    Long id,
    String name,
    Integer age,

    @JsonFormat(
        pattern = "yyyy-MM-dd"
    )
    LocalDate dob,
    String gender,
    String street,
    String state,
    String zip,
    String country,

    @JsonFormat(
        pattern = "yyyy-MM-dd hh:mm:ss"
    )
    LocalDateTime createdAt,

    @JsonFormat(
        pattern = "yyyy-MM-dd hh:mm:ss"
    )
    LocalDateTime updatedAt

) {

  public ProfileActionResponse(Profile profile) {
    this(
        profile.getId(), profile.getName(), profile.getAge(), profile.getDob(), profile.getGender().getValue(),
        profile.getStreet().value(), profile.getState().value(), profile.getZip().value(),
        profile.getCountry().value(), profile.getCreatedAt(), profile.getUpdatedAt()
    );
  }
}
