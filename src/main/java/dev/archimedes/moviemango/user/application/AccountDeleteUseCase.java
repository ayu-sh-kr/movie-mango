package dev.archimedes.moviemango.user.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.user.application.dtos.request.AccountDeleteRequest;
import dev.archimedes.moviemango.user.domain.AccountDeleteEvent;
import dev.archimedes.moviemango.user.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@RequiredArgsConstructor
public class AccountDeleteUseCase {

  private final AccountRepository accountRepository;
  private final ApplicationEventPublisher eventPublisher;


  protected void execute(AccountDeleteRequest request) {
    if (!accountRepository.existsById(request.id())) {
      throw new RuntimeException("Account do not exists");
    }

    accountRepository.deleteById(request.id());
    eventPublisher.publishEvent(new AccountDeleteEvent(request.id()));
  }

}
