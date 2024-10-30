package dev.archimedes.moviemango.cast.application;

import dev.archimedes.moviemango.cast.application.dto.request.CastCheckRequest;
import dev.archimedes.moviemango.cast.application.dto.request.CastCreateRequest;
import dev.archimedes.moviemango.cast.application.dto.request.CastDeleteRequest;
import dev.archimedes.moviemango.cast.application.dto.request.CastUpdateRequest;
import dev.archimedes.moviemango.cast.application.dto.response.CastActionResponse;
import dev.archimedes.moviemango.cast.domain.Cast;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CastController {

  private final CastCreateUseCase castCreateUseCase;
  private final CastUpdateUseCase castUpdateUseCase;
  private final CastDeleteUseCase castDeleteUseCase;
  private final CastCheckUseCase castCheckUseCase;

  @PostMapping("/v1/cast")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Use this API to create a cast profile")
  CastActionResponse create(CastCreateRequest request) {
    Cast executed = castCreateUseCase.execute(request);
    return new CastActionResponse(executed);
  }

  @PatchMapping("/v1/cast")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to update a cast profile")
  CastActionResponse update(CastUpdateRequest request) {
    Cast executed = castUpdateUseCase.execute(request);
    return new CastActionResponse(executed);
  }

  @DeleteMapping("/v1/cast")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Use this API to delete a cast profile")
  void delete(CastDeleteRequest request) {
    castDeleteUseCase.execute(request);
  }

  @GetMapping("/v1/cast/{id}/exists")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Use this API to check is a cast exist or not.")
  boolean check(@PathVariable("id") Long id) {
    return castCheckUseCase.execute(
        new CastCheckRequest(id)
    );
  }

}
