package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.application.dtos.request.AccountCreateRequest;
import dev.archimedes.moviemango.user.domain.Account;
import dev.archimedes.moviemango.user.domain.AccountEmail;
import dev.archimedes.moviemango.user.domain.AccountPassword;
import dev.archimedes.moviemango.user.domain.AccountRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AccountCreateUseCase {

  private final AccountRepository accountRepository;

  protected Account execute(AccountCreateRequest request) {

    if (accountRepository.existsByEmail(request.email())) {
      throw new RuntimeException("Account Already Exits");
    }

    Account account = new Account();
    account.setEmail(new AccountEmail(request.email()));
    account.setPassword(new AccountPassword(request.password()));
    return accountRepository.create(account);
  }

}
