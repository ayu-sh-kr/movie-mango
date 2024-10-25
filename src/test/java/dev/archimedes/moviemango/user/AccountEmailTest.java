package dev.archimedes.moviemango.user;

import dev.archimedes.moviemango.user.domain.AccountEmail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountEmailTest {

  @Test
  public void testEmailCannotBeNull() {
    Executable executable = () -> new AccountEmail(null);
    assertThrows(IllegalStateException.class, executable, "Email can't be null or blank");
  }

  @Test
  public void testEmailCannotBeBlank() {
    Executable executable = () -> new AccountEmail("");
    assertThrows(IllegalStateException.class, executable, "Email can't be null or blank");
  }

  @Test
  public void testEmailCannotBeInvalid() {
    Executable executable = () -> new AccountEmail("invalid-email");
    assertThrows(IllegalStateException.class, executable, "Value should be a valid email");
  }

  @Test
  public void testValidEmail() {
    new AccountEmail("test@example.com");
  }

  @Test
  public void testEmailValue() {
    AccountEmail accountEmail = new AccountEmail("test123@example.com");
    assertEquals(accountEmail.value(), "test123@example.com", "Value should match the equal value");
  }
}