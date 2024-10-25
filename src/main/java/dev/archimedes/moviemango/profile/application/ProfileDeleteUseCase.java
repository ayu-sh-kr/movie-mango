package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.UseCase;
import dev.archimedes.moviemango.profile.domain.ProfileRepository;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProfileDeleteUseCase {

  private final ProfileRepository profileRepository;

  void execute(Long id) {
    if(!profileRepository.existsById(id)) {
      throw new RuntimeException("Profile to deleted not found");
    }
    profileRepository.deleteById(id);
  }

}
