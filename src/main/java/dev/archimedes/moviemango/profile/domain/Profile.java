package dev.archimedes.moviemango.profile.domain;

import dev.archimedes.moviemango.application.ApplicationUtils;
import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Profile {

  private Long id;
  private String name;
  private Integer age;
  private LocalDate dob;
  private Street street;
  private State state;
  private Zip zip;
  private Country country;
  private Long refer;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @SuppressWarnings("unused")
  public Profile(
      String name, Integer age, LocalDate dob, String street, String state, String zip,
      String country, Long refer, LocalDateTime createdAt, LocalDateTime updatedAt
  ) {

    Assert.isTrue(StringUtils.isNotBlank(name), "Name can't be blank or null.");
    Assert.isTrue(ApplicationUtils.validAge(age, dob), "Age should be valid wrt to dob.");
    Assert.notNull(refer, "Reference can't be null.");

    this.name = name;
    this.age = age;
    this.dob = dob;
    this.street = new Street(street);
    this.state = new State(state);
    this.zip = new Zip(zip);
    this.country = new Country(country);
    this.refer = refer;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
