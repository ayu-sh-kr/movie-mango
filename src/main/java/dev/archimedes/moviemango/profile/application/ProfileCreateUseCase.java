package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.profile.application.dto.request.ProfileCreateRequest;
import dev.archimedes.moviemango.profile.domain.Profile;
import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProfileCreateUseCase {

  private final ProfileRepository profileRepository;


  Profile execute(ProfileCreateRequest request, Long id) {

    if (profileRepository.existsByAccountId(id)) {
      throw new RuntimeException("Profile already created use update API");
    }

    return profileRepository.save(
        new Profile(
          request.name(), request.age(), request.dob(), request.street(),
          request.state(), request.zip(), request.country(), id, null, null
        )
    );

  }
}
