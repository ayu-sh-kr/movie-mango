package dev.archimedes.moviemango.user.domain;

import lombok.*;

import java.time.LocalDateTime;

/// The **Account** class represents a user account entity in the system.
///
/// It includes fields for the account ID, email, password, and timestamps for creation and last update.
/// The class uses Lombok annotations to reduce boilerplate code.
///
/// Attributes:
///
///     - id: Unique identifier for the account.
///     - email: Email address associated with the account, encapsulated in an [AccountEmail] object.
///     - password: Password for the account, encapsulated in an [AccountPassword] object.
///     - createdAt: Timestamp when the account was created.
///     - updatedAt: Timestamp when the account was last updated.
///
///
/// @see AccountEmail
/// @see AccountPassword
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

  private Long id;

  private AccountEmail email;

  private AccountPassword password;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @SuppressWarnings("unused")
  public Account(Long id, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.email = new AccountEmail(email);
    this.password = new AccountPassword(password);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
