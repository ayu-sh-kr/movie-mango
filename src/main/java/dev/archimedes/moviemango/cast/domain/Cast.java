package dev.archimedes.moviemango.cast.domain;

import dev.archimedes.moviemango.application.ApplicationUtils;
import dev.archimedes.moviemango.profile.domain.Country;
import dev.archimedes.moviemango.profile.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Cast {

  private Long id;
  private CastName name;
  private CastAge age;
  private CastDob dob;
  private Gender gender;
  private Country country;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Cast(String name, Integer age, LocalDate dob, String gender, String country, LocalDateTime createdAt, LocalDateTime updatedAt) {

    Assert.isTrue(
        ApplicationUtils.validAge(age, dob),
        "Age and DOB must be valid"
    );

    this.name = new CastName(name);
    this.age = new CastAge(age);
    this.dob = new CastDob(dob);
    this.gender = new Gender(gender);
    this.country = new Country(country);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
