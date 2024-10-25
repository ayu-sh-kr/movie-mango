package dev.archimedes.moviemango.user.domain;

import lombok.*;

import java.time.LocalDateTime;

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
