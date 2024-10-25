package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import dev.archimedes.moviemango.user.domain.AccountDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountActionListener {

  private final ProfileRepository profileRepository;

  public AccountActionListener(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @Async
  @EventListener
  @Transactional
  public void handleAccountDelete(AccountDeleteEvent event) {
    if (profileRepository.existsById(event.id())) {
      profileRepository.deleteByAccountId(event.id());
    }
  }

}
