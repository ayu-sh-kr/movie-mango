package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import dev.archimedes.moviemango.user.domain.AccountDeleteEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class AccountActionListener {

  private final ProfileRepository profileRepository;

  public AccountActionListener(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  @ApplicationModuleListener
  public void handleAccountDelete(AccountDeleteEvent event) {
    if (profileRepository.existsById(event.id())) {
      profileRepository.deleteByAccountId(event.id());
    }
  }

}
