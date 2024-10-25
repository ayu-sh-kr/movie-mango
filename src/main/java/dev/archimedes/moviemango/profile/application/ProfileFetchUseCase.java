package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.profile.domain.Profile;
import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class ProfileFetchUseCase {

  private final ProfileRepository profileRepository;

  Profile execute(Long id) {
    Optional<Profile> optional = profileRepository.findByAccountId(id);
    if (optional.isPresent()) {
      return optional.get();
    }

    throw new RuntimeException("No profile found for give account id: " + id);

  }

}
