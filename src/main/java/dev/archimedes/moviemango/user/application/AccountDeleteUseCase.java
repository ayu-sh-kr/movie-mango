package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.application.dtos.request.AccountDeleteRequest;
import dev.archimedes.moviemango.user.domain.AccountDeleteEvent;
import dev.archimedes.moviemango.user.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.Assert;

/// **AccountDeleteUseCase** class is a use case service to delete the account from the system.
///
///   The main responsibility is to validate if the account exist or not and then perform delete using
///   [AccountRepository] and publish the delete event to delete [dev.archimedes.moviemango.profile.domain.Profile]
///   associated with the [dev.archimedes.moviemango.user.domain.Account]
///
/// @see AccountDeleteUseCase#execute(AccountDeleteRequest)
/// @see dev.archimedes.moviemango.user.domain.Account
/// @see AccountDeleteRequest
/// @see AccountDeleteEvent
@UseCase
@RequiredArgsConstructor
public class AccountDeleteUseCase {

  private final AccountRepository accountRepository;
  private final ApplicationEventPublisher eventPublisher;


  /// Method to perform the delete action on Account upon delete request.
  ///
  /// Perform validation for existence of the account and throws error if fails.
  ///
  /// Throws [IllegalStateException]
  /// @param request the request object containing the account ID to be deleted.
  ///
  protected void execute(AccountDeleteRequest request) {

    Assert.state(
        accountRepository.existsById(request.id()),
        "Account doesn't not exist"
    );

    accountRepository.deleteById(request.id());
    eventPublisher.publishEvent(new AccountDeleteEvent(request.id()));
  }

}
