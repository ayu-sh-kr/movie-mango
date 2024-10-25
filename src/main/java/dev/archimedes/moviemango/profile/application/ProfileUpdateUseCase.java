package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.profile.application.dto.request.ProfileUpdateRequest;
import dev.archimedes.moviemango.profile.domain.Profile;
import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProfileUpdateUseCase {

  private final ProfileRepository profileRepository;

  Profile execute(ProfileUpdateRequest request, Long profileId, Long accountId) {

    if (!profileRepository.existsById(profileId)) {
      throw new RuntimeException("Trying to modify profile that do not exists. Profile Id: " + profileId);
    }

    Profile profile = new Profile(
      request.name(), request.age(), request.dob(), request.street(), request.state(), request.zip(),
      request.country(), accountId, null, null
    );

    profile.setId(profileId);
    return profileRepository.save(
        profile
    );
  }
}
