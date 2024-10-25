package dev.archimedes.moviemango.profile.application;

import dev.archimedes.moviemango.profile.application.dto.request.ProfileCreateRequest;
import dev.archimedes.moviemango.profile.application.dto.request.ProfileUpdateRequest;
import dev.archimedes.moviemango.profile.application.dto.response.ProfileActionResponse;
import dev.archimedes.moviemango.profile.domain.Profile;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {


  private final ProfileCreateUseCase profileCreateUseCase;
  private final ProfileFetchUseCase profileFetchUseCase;
  private final ProfileDeleteUseCase profileDeleteUseCase;
  private final ProfileUpdateUseCase profileUpdateUseCase;

  @PostMapping("/v1/profile")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Use this API to create the profile of the user.")
  public ProfileActionResponse create(@RequestBody ProfileCreateRequest request, @RequestParam Long id) {
    Profile executed = profileCreateUseCase.execute(request, id);
    return new ProfileActionResponse(
        executed
    );
  }

  @GetMapping("/v1/profile")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to fetch the profile of the user by its ID.")
  public ProfileActionResponse fetchById(@RequestParam Long id) {
    Profile executed = profileFetchUseCase.execute(id);
    return new ProfileActionResponse(
        executed
    );
  }


  @DeleteMapping("/v1/profile")
  @ResponseStatus(HttpStatus.ACCEPTED)
  @Operation(summary = "Use this API to delete the profile using profile id.")
  public void deleteById(@RequestParam Long profileId) {
    profileDeleteUseCase.execute(profileId);
  }

  @PatchMapping("/v1/profile")
  @ResponseStatus(HttpStatus.ACCEPTED)
  @Operation(summary = "Use this API to update the profile record for given profile id.")
  public ProfileActionResponse updateProfileById(
      @RequestParam("profile_id") Long profileId,
      @RequestParam("account_id") Long accountId,
      @RequestBody ProfileUpdateRequest request
      ) {
    Profile updated = profileUpdateUseCase.execute(request, profileId, accountId);
    return new ProfileActionResponse(
        updated
    );
  }

}
