package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.application.dtos.request.AccountCreateRequest;
import dev.archimedes.moviemango.user.domain.Account;
import dev.archimedes.moviemango.user.domain.AccountEmail;
import dev.archimedes.moviemango.user.domain.AccountPassword;
import dev.archimedes.moviemango.user.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

/// The **AccountCreateUseCase** class is a use case service responsible for handling the creation of new user accounts.
///
/// This class is annotated with `@UseCase`, indicating that it is a Spring service component and its methods should
/// be executed within a transactional context. It uses the `AccountRepository` to interact with the persistence layer.
///
/// The main responsibility of this class is to validate the account creation request and create a new account if it
/// does not already exist. It ensures that the email provided in the request is unique and throws a `IllegalStateException`
/// if an account with the same email already exists.
///
/// @see dev.archimedes.moviemango.UseCase
/// @see dev.archimedes.moviemango.user.domain.AccountRepository
/// @see dev.archimedes.moviemango.user.application.dtos.request.AccountCreateRequest
/// @see dev.archimedes.moviemango.user.domain.Account
/// @see dev.archimedes.moviemango.user.domain.AccountEmail
/// @see dev.archimedes.moviemango.user.domain.AccountPassword
@UseCase
@RequiredArgsConstructor
public class AccountCreateUseCase {

  private final AccountRepository accountRepository;

  protected Account execute(AccountCreateRequest request) {

    Assert.state(
        !accountRepository.existsByEmail(request.email()),
        "Email already in use"
    );

    Account account = new Account();
    account.setEmail(new AccountEmail(request.email()));
    account.setPassword(new AccountPassword(request.password()));
    return accountRepository.create(account);
  }

}
