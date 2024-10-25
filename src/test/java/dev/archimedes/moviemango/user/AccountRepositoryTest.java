package dev.archimedes.moviemango.user;

import dev.archimedes.moviemango.user.domain.Account;
import dev.archimedes.moviemango.user.domain.AccountEmail;
import dev.archimedes.moviemango.user.domain.AccountPassword;
import dev.archimedes.moviemango.user.domain.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.simple.JdbcClient;

@DataJdbcTest
public class AccountRepositoryTest {

  @Autowired
  private JdbcClient jdbcClient;

  private AccountRepository accountRepository;

  @BeforeEach
  void setUp() {
    accountRepository = new AccountRepository(jdbcClient);
  }


  @Test
  void shouldHaveAnIdOnSave() {
    Account account = new Account();
    account.setPassword(new AccountPassword("password"));
    account.setEmail(new AccountEmail("test@example.com"));
    Account saved = accountRepository.save(account);
    Assertions.assertNotNull(saved.getId(), "Id must be generated on the save");
  }


  @Test
  void shouldHaveUpdateTimeStamp() {
    Account account = new Account();
    account.setEmail(new AccountEmail("test@example.com"));
    account.setPassword(new AccountPassword("password"));
    account = accountRepository.save(account);
    account = accountRepository.save(account);
    Assertions.assertNotNull(account.getUpdatedAt(), "Update timestamp should exists");
  }

}
