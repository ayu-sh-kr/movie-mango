package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.domain.Account;
import dev.archimedes.moviemango.user.domain.AccountRepository;

@UseCase
public class AccountFetchUseCase {

  private final AccountRepository accountRepository;

  public AccountFetchUseCase(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  protected Account execute(Long id) {
    if (accountRepository.existsById(id)) {
      return accountRepository.getReferenceById(id);
    }

    throw new RuntimeException("Account not exists");

  }
}
