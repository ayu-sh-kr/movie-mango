package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.domain.Account;
import dev.archimedes.moviemango.user.domain.AccountRepository;

/// **AccountFetchUseCase** class is a use case service class responsible fetching the account details.
///
/// The main responsibility of this class is to validate the account fetch request if the account exist or not.
/// If exist then fetch the detail and return it.
///
/// Methods:
///
///  - [#execute(Long)]
///
///  @see dev.archimedes.moviemango.user.application.AccountFetchUseCase#execute(Long)
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
