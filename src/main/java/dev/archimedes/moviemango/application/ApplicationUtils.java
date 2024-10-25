package dev.archimedes.moviemango.application;

import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ApplicationUtils {

  /**
   * Validates if the provided email address is in a valid format.
   *
   * @param value the email address to validate
   * @return true if the email address is in a valid format, otherwise false
   */
  public static boolean isValidEmail(String value) {
    String EMAIL_REGEX = "^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$";
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    return pattern.matcher(value).matches();
  }


  /**
   * Validates if the provided age matches the age calculated from the given date of birth.
   *
   * @param age the age to validate, must not be null
   * @param dob the date of birth to calculate the age from, must not be null
   * @return true if the calculated age matches the provided age, otherwise false
   * @throws IllegalArgumentException if age or dob is null
   */
  public static boolean validAge(Integer age, LocalDate dob) {

    Assert.notNull(age, "Age to compare can't be null");
    Assert.notNull(dob, "Date of birth can't be null");

    LocalDate today = LocalDate.now();
    int calculatedAge = today.getYear() - dob.getYear();
    if (dob.plusYears(calculatedAge).isAfter(today)) {
      calculatedAge --;
    }

    return calculatedAge == age;
  }

}
