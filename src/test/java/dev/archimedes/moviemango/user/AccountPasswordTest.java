package dev.archimedes.moviemango.user;

import dev.archimedes.moviemango.user.domain.AccountPassword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountPasswordTest {
  @Test
  public void testPasswordCannotBeNull() {
    Executable executable = () -> new AccountPassword(null);
    assertThrows(IllegalStateException.class, executable, "Email can't be null");
  }

  @Test
  public void testPasswordCannotBeBlank() {
    Executable executable = () -> new AccountPassword("");
    assertThrows(IllegalStateException.class, executable, "Password can't be blank");
  }

  @Test
  public void testPasswordCannotBeShorterThanEightCharacters() {
    Executable executable = () -> new AccountPassword("short");
    assertThrows(IllegalStateException.class, executable, "Minimum password length is 8 character");
  }

  @Test
  public void testValidPassword() {
    new AccountPassword("validPassword123");
  }

  @Test
  public void testPasswordValue() {
    AccountPassword password = new AccountPassword("password");
    assertEquals(password.value(), "password", "Value should match the actual value");
  }
}
